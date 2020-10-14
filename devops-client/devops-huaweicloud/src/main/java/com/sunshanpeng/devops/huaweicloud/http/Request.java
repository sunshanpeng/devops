package com.sunshanpeng.devops.huaweicloud.http;

import com.sunshanpeng.devops.common.util.EncryptUtil;
import com.sunshanpeng.devops.huaweicloud.conts.CBHConst;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Request {

    public static String TOKEN ;
    public static final ThreadLocal<Long> TIMESTAMP = ThreadLocal.withInitial(() -> null);
    public static final ThreadLocal<Long> LOCALTIME = ThreadLocal.withInitial(() -> null);


    public static Response cbhPost(String url, String body, boolean encrypt) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if (encrypt) {
            String token = TOKEN.substring(8, 24);
            body = EncryptUtil.encrypt(body, token, CBHConst.IV);
        }

        long timestamp = new Date().getTime();
        if (TIMESTAMP.get() != null) {
            long time_diff = new Date().getTime() - LOCALTIME.get();
            timestamp = TIMESTAMP.get();
            timestamp += time_diff;
        }
//        String randomAlphabetic = RandomStringUtils.randomAlphanumeric(16);
        String randomAlphabetic = CBHConst.IV;
        String nonce = randomAlphabetic + timestamp;
        nonce = EncryptUtil.SHA256(nonce);

        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(body, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);

        //伪装浏览器
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Referer", CBHConst.CBH_HOST);
        httpPost.addHeader("Origin", CBHConst.CBH_HOST);
        httpPost.addHeader("timestamp", String.valueOf(timestamp));
        httpPost.addHeader("nonce", nonce);
        httpPost.addHeader("cookie", "YAB_AUTH_TOKEN=" + TOKEN);

        return doRequest(httpPost);
    }

    private static Response doRequest(HttpUriRequest request) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        // 创建Httpclient对象
        try (CloseableHttpClient httpclient = createHttpClient();
             CloseableHttpResponse closeableHttpResponse = httpclient.execute(request)) {
            return Response.builder().statusCode(closeableHttpResponse.getStatusLine().getStatusCode())
                    .content(EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"))
                    .build();
        }
    }

    private static CloseableHttpClient createHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null,
                new NoopHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }
}
