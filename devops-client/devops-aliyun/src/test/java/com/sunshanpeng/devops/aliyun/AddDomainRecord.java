package com.sunshanpeng.devops.aliyun;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.alidns.model.v20150109.*;

/**
 * @see <a href="https://help.aliyun.com/document_detail/124923.html?spm=a2c4g.11186623.6.625.16464256cS7Wi1"/>
 */
public class AddDomainRecord {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "<accessKeyId>",
                "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        AddDomainRecordRequest request = new AddDomainRecordRequest();
        request.setValue("3.0.3.0");
        request.setType("A");
        request.setRR("apitest");
        request.setDomainName("dns-example.com");

        try {
            AddDomainRecordResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}
