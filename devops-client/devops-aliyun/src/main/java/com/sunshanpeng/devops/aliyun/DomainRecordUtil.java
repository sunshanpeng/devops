package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.sunshanpeng.devops.common.exception.BusinessException;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;
import com.sunshanpeng.devops.resource.enums.RecordStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
public class DomainRecordUtil {

    private AliyunClient aliyunClient;

    /**
     * 获取域名解析记录
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
     * 获取域名解析记录
     * @param subDomainName 域名记录
     * @return 一条或多条域名解析记录
     */
    public List<DescribeSubDomainRecordsResponse.Record> subDomain(@NotEmpty String subDomainName) throws ClientException {
        DescribeSubDomainRecordsRequest request = new DescribeSubDomainRecordsRequest();
        request.setSubDomain(subDomainName);
        DescribeSubDomainRecordsResponse response = aliyunClient.getResponse(request);
        return response.getDomainRecords();
    }

    /**
     * 新增或者更新解析记录
     * @param domainRecord
     * @throws ClientException
     */
    public void addOrUpdate(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        List<DescribeSubDomainRecordsResponse.Record> records = subDomain(domainRecord.getFullDomain()).stream()
                .filter(record -> RecordStatusEnum.ENABLE.getAliPrivateZone().equals(record.getStatus()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(records)) {
            addDomainRecord(domainRecord);
            return;
        }

        if (records.size() > 1) {
            throw new BusinessException("解析记录超过1条，暂时不支持更新");
        }

        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
        updateDomainRecordRequest.setRecordId(records.get(0).getRecordId());
        updateDomainRecordRequest.setValue(domainRecord.getValue());
        updateDomainRecordRequest.setRR(domainRecord.getRr());
        updateDomainRecordRequest.setType(domainRecord.getRecordType().getValue());
        aliyunClient.doAction(updateDomainRecordRequest);
    }

    /**
     * 新增解析记录
     * @param domainRecord
     * @throws ClientException
     */
    public void addIfNotExist(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        List<DescribeSubDomainRecordsResponse.Record> records = subDomain(domainRecord.getFullDomain()).stream()
                .filter(record -> RecordStatusEnum.ENABLE.getAliPrivateZone().equals(record.getStatus()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(records)) {
            return;
        }
        addDomainRecord(domainRecord);
    }
    /**
     * 删除解析记录
     * @param domainName
     * @throws ClientException
     */
    public void delete(@NotEmpty String domainName) throws ClientException {
        List<DescribeSubDomainRecordsResponse.Record> records = subDomain(domainName);
        if (CollectionUtils.isEmpty(records)) {
            return;
        }
        records.stream().map(DescribeSubDomainRecordsResponse.Record::getRecordId)
                .forEach(recordId ->{
                    DeleteDomainRecordRequest deleteDomainRecordRequest = new DeleteDomainRecordRequest();
                    deleteDomainRecordRequest.setRecordId(recordId);
                    try {
                        aliyunClient.doAction(deleteDomainRecordRequest);
                    } catch (ClientException e) {
                        log.error(String.format("domainName: %s", domainName), e);
                        throw new BusinessException(String.format("删除解析记录异常: %s", domainName));
                    }
                });
    }

    /**
     * 设置解析记录状态
     * @param domainName
     * @param statusEnum
     * @throws ClientException
     */
    public void setStatus(String domainName, RecordStatusEnum statusEnum) throws ClientException {
        List<DescribeSubDomainRecordsResponse.Record> records = subDomain(domainName);
        if (CollectionUtils.isEmpty(records)) {
            throw new BusinessException(String.format("解析记录不存在: %s", domainName));
        }
        records.stream().map(DescribeSubDomainRecordsResponse.Record::getRecordId)
                .forEach(recordId ->{
                    SetDomainRecordStatusRequest request = new SetDomainRecordStatusRequest();
                    request.setRecordId(recordId);
                    request.setStatus(statusEnum.getAliDNS());
                    try {
                        aliyunClient.doAction(request);
                    } catch (ClientException e) {
                        log.error(String.format("domainName: %s", domainName), e);
                        throw new BusinessException(String.format("设置解析记录状态异常: %s", domainName));
                    }
                });
    }

    public DomainRecordUtil(AliyunClient aliyunClient) {
        this.aliyunClient = aliyunClient;
    }

    private void addDomainRecord(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        AddDomainRecordRequest addDomainRecordRequest = new AddDomainRecordRequest();
        addDomainRecordRequest.setDomainName(domainRecord.getDomainName());
        addDomainRecordRequest.setRR(domainRecord.getRr());
        addDomainRecordRequest.setValue(domainRecord.getValue());
        addDomainRecordRequest.setType(domainRecord.getRecordType().getValue());
        aliyunClient.doAction(addDomainRecordRequest);
    }
}
