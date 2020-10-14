package com.sunshanpeng.devops.huaweicloud.cbh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunshanpeng.devops.huaweicloud.conts.CBHConst;
import com.sunshanpeng.devops.huaweicloud.http.Request;
import com.sunshanpeng.devops.huaweicloud.http.Response;

public class User extends Base {
    private static void userList() throws Exception {
        Response response =
                Request.cbhPost(CBHConst.USER_LIST, CBHConst.PAGE_JSON, true);
        String content = response.getContent();
        if (response.getStatusCode() == 200) {
            JSONObject jsonObject = JSON.parseObject(content);
            if (jsonObject.getJSONObject("data") != null) {
                System.out.println(jsonObject.getJSONObject("data"));
            }
            return;
        }
        throw new RuntimeException(content);
    }

    private static void accountList() throws Exception {
        Response response =
                Request.cbhPost(CBHConst.ACCOUNT_LIST, CBHConst.PAGE_ID_JSON, true);
        String content = response.getContent();
        if (response.getStatusCode() == 200) {
            JSONObject jsonObject = JSON.parseObject(content);
            if (jsonObject.getJSONObject("data") != null) {
                System.out.println(jsonObject.getJSONObject("data"));
            }
            return;
        }
        throw new RuntimeException(content);
    }
    public static void main(String[] args) throws Exception {
        config();
        login();
        accountList();
    }
}
