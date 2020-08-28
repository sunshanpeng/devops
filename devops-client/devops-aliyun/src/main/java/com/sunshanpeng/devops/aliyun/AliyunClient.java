package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliyunClient {

    private IAcsClient client;

    public AliyunClient(String regionId, String accessKeyId, String accessKeySecret) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }

    public AliyunClient(IAcsClient client) {
        this.client = client;
    }

    public <T extends AcsResponse> T getResponse(AcsRequest<T> request) throws ClientException {
        return client.getAcsResponse(request);
    }

    public <T extends AcsResponse> void doAction(AcsRequest<T> request) throws ClientException {
        client.doAction(request);
    }
}
