package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
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

    public DomainRecordUtil(AliyunClient aliyunClient) {
        this.aliyunClient = aliyunClient;
    }
}
