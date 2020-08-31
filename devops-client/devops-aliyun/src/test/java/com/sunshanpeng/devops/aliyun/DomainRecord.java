package com.sunshanpeng.devops.aliyun;


import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;
import com.sunshanpeng.devops.resource.enums.RecordTypeEnum;
import org.junit.Test;

import java.util.List;

public class DomainRecord {
   private static DomainRecordUtil domainRecordUtil = new DomainRecordUtil(new AliyunClient(Constants.REGION_ID, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET));
    private static final String DOMAIN_NAME = "sunshanpeng.com";
    public static void main(String[] args) {
        DomainRecordDTO domainRecordDTO = DomainRecordDTO.builder()
                .domainName(DOMAIN_NAME).build();
        try {
            List<DescribeDomainRecordsResponse.Record> list = domainRecordUtil.list(domainRecordDTO);
            for (DescribeDomainRecordsResponse.Record record : list) {
                System.out.println(record.getRR()+"."+record.getDomainName() + ":" + record.getValue());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOrUpdate() throws ClientException {
        DomainRecordDTO domainRecordDTO = DomainRecordDTO.builder()
                .rr("java.test").recordType(RecordTypeEnum.A).value("101.101.101.101")
                .domainName(DOMAIN_NAME).build();
        domainRecordUtil.addOrUpdate(domainRecordDTO);
    }

    @Test
    public void testDelete() throws ClientException {
        domainRecordUtil.delete("java.test.sunshanpeng.com");
    }
}
