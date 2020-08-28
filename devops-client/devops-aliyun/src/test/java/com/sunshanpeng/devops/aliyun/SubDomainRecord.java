package com.sunshanpeng.devops.aliyun;


import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;

public class SubDomainRecord {
   private static DomainRecordUtil domainRecordUtil = new DomainRecordUtil(new AliyunClient(Constants.REGION_ID, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET));

    public static void main(String[] args) {
        try {
            List<DescribeSubDomainRecordsResponse.Record> list = domainRecordUtil.subDomain("blog.dev.sunshanpeng.com");
            for (DescribeSubDomainRecordsResponse.Record record : list) {
                System.out.println(record.getRR()+"."+record.getDomainName() + ":" + record.getValue());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
