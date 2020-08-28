package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public class DomainRecordUtil {

    private AliyunClient aliyunClient;

    /**
     * 获取域名记录
     * @param domainRecord 域名记录
     * @return
     */
    public List<DescribeDomainRecordsResponse.Record> list(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName(domainRecord.getDomainName());
        request.setRRKeyWord(domainRecord.getRr());
        request.setPageSize(100L);
        DescribeDomainRecordsResponse response = aliyunClient.getResponse(request);
        return response.getDomainRecords();
    }

    /**
     * 获取域名记录
     * @param subDomainName 域名记录
     * @return 一条或多条域名解析记录
     */
    public List<DescribeSubDomainRecordsResponse.Record> subDomain(String subDomainName) throws ClientException {
        DescribeSubDomainRecordsRequest request = new DescribeSubDomainRecordsRequest();
        request.setSubDomain(subDomainName);
        DescribeSubDomainRecordsResponse response = aliyunClient.getResponse(request);
        return response.getDomainRecords();
    }

    public DomainRecordUtil(AliyunClient aliyunClient) {
        this.aliyunClient = aliyunClient;
    }
}
