package com.sunshanpeng.devops.aliyun;


import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;

import java.util.List;

public class DomainRecord {
   private static DomainRecordUtil domainRecordUtil = new DomainRecordUtil(new AliyunClient(Constants.REGION_ID, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET));

    public static void main(String[] args) {
        DomainRecordDTO domainRecordDTO = DomainRecordDTO.builder()
                .domainName("sunshanpeng.com").build();
        try {
            List<DescribeDomainRecordsResponse.Record> list = domainRecordUtil.list(domainRecordDTO);
            for (DescribeDomainRecordsResponse.Record record : list) {
                System.out.println(record.getRR()+"."+record.getDomainName() + ":" + record.getValue());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
