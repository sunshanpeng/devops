package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;

public class DomainRecordUtil {

    private AliyunClient aliyunClient;

    /**
     * 获取域名记录
     * @param domainName 域名（sunshanpeng.com）
     * @return
     */
    public List<DescribeDomainRecordsResponse.Record> list(String domainName) throws ClientException {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName(domainName);
        DescribeDomainRecordsResponse response = aliyunClient.getResponse(request);
        return response.getDomainRecords();
    }
}
