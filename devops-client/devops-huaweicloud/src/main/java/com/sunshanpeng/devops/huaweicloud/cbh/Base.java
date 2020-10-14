package com.sunshanpeng.devops.huaweicloud.cbh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunshanpeng.devops.common.util.EncryptUtil;
import com.sunshanpeng.devops.huaweicloud.conts.CBHConst;
import com.sunshanpeng.devops.huaweicloud.http.Request;
import com.sunshanpeng.devops.huaweicloud.http.Response;

import java.util.Date;

public class Base {

    public static void config() throws Exception {
        Response response =
                Request.cbhPost(CBHConst.CONFIG, CBHConst.CONFIG_JSON, false);

        String content = response.getContent();
        if (response.getStatusCode() == 200) {
            JSONObject jsonObject = JSON.parseObject(content);
            if (jsonObject.getJSONObject("heartCount") != null
                    && jsonObject.getJSONObject("heartCount").getLong("timestamp") != null) {
                Long timestamp = jsonObject.getJSONObject("heartCount").getLong("timestamp");
                Request.TIMESTAMP.set(timestamp);
                Request.LOCALTIME.set(new Date().getTime());
                return;
            }
        }
        throw new RuntimeException(content);
    }

    public static void login() throws Exception {
        JSONObject b = new JSONObject();
        b.put("username", System.getenv("CBH_USERNAME"));
        String encrypt = EncryptUtil.encrypt(System.getenv("CBH_PWD"), CBHConst.KEY, CBHConst.IV);
        b.put("pwd", encrypt);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("c", "{}");
        jsonObject.put("b", b.toString());
        Response response = Request.cbhPost(CBHConst.LOGIN_URL, jsonObject.toString(), false);
        String content = response.getContent();
        if (response.getStatusCode() == 200) {
            JSONObject json = JSON.parseObject(content);
            if (json.getJSONObject("data") != null
                    && json.getJSONObject("data").getString("token") != null) {
                String token = json.getJSONObject("data").getString("token");
                Request.TOKEN = token;
                return;
            }
        }
        throw new RuntimeException(content);
    }

    public static void main(String[] args) throws Exception {
        config();
        login();
    }
}
