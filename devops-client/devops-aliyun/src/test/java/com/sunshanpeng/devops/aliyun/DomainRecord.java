package com.sunshanpeng.devops.aliyun;


import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;

public class DomainRecord extends BaseClient {
    public static void main(String[] args) {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName("sunshanpeng.com");

        DescribeDomainRecordsResponse response;
        try {
            response = client.getAcsResponse(request);
            List<DescribeDomainRecordsResponse.Record> list = response.getDomainRecords();
            for (DescribeDomainRecordsResponse.Record record : list) {
                System.out.println(record.getRR()+"."+record.getDomainName() + ":" + record.getValue());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
