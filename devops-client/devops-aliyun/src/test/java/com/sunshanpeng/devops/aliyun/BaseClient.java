package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public abstract class BaseClient {
    protected static IAcsClient client = null;

    static {
        String regionId = Constants.REGION_ID; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = Constants.ACCESS_KEY_ID; // your accessKey
        String accessKeySecret = Constants.ACCESS_KEY_SECRET;// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }
}
