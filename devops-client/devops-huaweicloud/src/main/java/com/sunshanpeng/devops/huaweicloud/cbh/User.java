package com.sunshanpeng.devops.huaweicloud.cbh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunshanpeng.devops.common.util.EncryptUtil;
import com.sunshanpeng.devops.huaweicloud.conts.CBHConst;
import com.sunshanpeng.devops.huaweicloud.http.Request;
import com.sunshanpeng.devops.huaweicloud.http.Response;

public class User extends Base {

    public static void addUser() throws Exception {
        JSONObject b = new JSONObject();
        b.put("loginName", "sunshanpeng11111");
        String encrypt = EncryptUtil.encrypt("12345", CBHConst.KEY, CBHConst.IV);
        b.put("pwd", encrypt);
        b.put("name", "孙善鹏111");
        b.put("phone", "13105670660");
        b.put("email", "13105670660@163.com");
        b.put("roleId", 10);
        b.put("departmentId", 10);
        b.put("verifyType", 1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("c", "{}");
        jsonObject.put("b", b.toString());
        Response response =
                Request.cbhPost(CBHConst.ADD_USER, jsonObject.toJSONString(), true);
        String content = response.getContent();
        if (response.getStatusCode() == 200) {
            return;
        }
        throw new RuntimeException(content);
    }

    public static void userList() throws Exception {
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

    public static void accountList() throws Exception {
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

    public static void userDetail() throws Exception {
        Response response =
                Request.cbhPost(CBHConst.USER_DETAIL, CBHConst.PAGE_ID_JSON, true);
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
        addUser();
    }
}
