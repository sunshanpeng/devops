package com.sunshanpeng.devops.huaweicloud.conts;

public interface CBHConst {
    String KEY = "tmppasswordver1.";
    String IV = "rUIEX913ZOTXi3d7";
    String CBH_HOST = "https://jumper-hw.idanchuang.net/";

    String LOGIN_URL = CBH_HOST + "/3.0/authService/login";
    String USER_LIST = CBH_HOST + "/3.0/userService/userList";
    String ADD_USER = CBH_HOST + "/3.0/userService/addUser";
    String USER_DETAIL = CBH_HOST + "/3.0/userService/userDetail";
    String USER_GROUP_LIST = CBH_HOST + "/3.0/userGroupService/userGroupList";
    String ACCOUNT_LIST = CBH_HOST + "/3.0/userService/accountList";
    String CONFIG = CBH_HOST + "/3.0/authService/config";


    String PAGE_JSON = "{\"c\": {}, \"b\": {\"page\": 1, \"pageSize\": 200}}";
    String PAGE_ID_JSON = "{\"c\": {}, \"b\": {\"id\": 20,\"page\": 1, \"pageSize\": 200}}";
    String CONFIG_JSON = "{\"c\": {}, \"b\": {}}";
}
