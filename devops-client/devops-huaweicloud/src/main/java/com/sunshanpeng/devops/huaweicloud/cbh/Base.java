package com.sunshanpeng.devops.huaweicloud.cbh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    public static void main(String[] args) throws Exception {
        config();
    }
}
