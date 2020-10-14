package com.sunshanpeng.devops.huaweicloud.conts;

public interface CBHConst {
    String KEY = "tmppasswordver1.";
    String IV = "rUIEX913ZOTXi3d7";
    String CBH_HOST = "https://jumper-hw.idanchuang.net/";

    String LOGIN_URL = CBH_HOST + "/3.0/authService/login";
    String USER_LIST = CBH_HOST + "/3.0/userService/userList";
    String USER_DETAIL = CBH_HOST + "/3.0/userService/userDetail";
    String USER_GROUP_LIST = CBH_HOST + "/3.0/userGroupService/userGroupList";
    String CONFIG = CBH_HOST + "/3.0/authService/config";


    public static final String PAGE_JSON = "{\"c\": {}, \"b\": {\"page\": 1, \"pageSize\": 200}}";
    public static final String CONFIG_JSON = "{\"c\": {}, \"b\": {}}";
}
