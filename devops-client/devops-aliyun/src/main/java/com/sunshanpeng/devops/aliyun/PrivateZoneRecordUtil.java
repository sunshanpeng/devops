package com.sunshanpeng.devops.aliyun;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.pvtz.model.v20180101.*;
import com.google.common.collect.Lists;
import com.sunshanpeng.devops.common.exception.BaseException;
import com.sunshanpeng.devops.common.core.util.JsonUtil;
import com.sunshanpeng.devops.resource.dto.DomainRecordDTO;
import com.sunshanpeng.devops.resource.enums.RecordStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
public class PrivateZoneRecordUtil {
    private AliyunClient aliyunClient;

    /**
     * 获取域名解析记录
     * @param domainRecord 域名记录
     * @return
     */
    public List<DescribeZoneRecordsResponse.Record> list(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        DescribeZoneRecordsRequest request = new DescribeZoneRecordsRequest();
        request.setZoneId(getZoneId(domainRecord.getDomainName()));
        request.setKeyword(domainRecord.getRr());
        request.setPageSize(100);
        DescribeZoneRecordsResponse response = aliyunClient.getResponse(request);
        return response.getRecords();
    }

    /**
     * 新增或者更新解析记录
     * @param domainRecord
     * @throws ClientException
     */
    public void addOrUpdate(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        List<DescribeZoneRecordsResponse.Record> records = list(domainRecord);
        if (CollectionUtils.isEmpty(records)) {
            AddZoneRecordRequest request = new AddZoneRecordRequest();
            request.setZoneId(getZoneId(domainRecord.getDomainName()));
            request.setRr(domainRecord.getRr());
            request.setValue(domainRecord.getValue());
            request.setType(domainRecord.getRecordType().getValue());
            aliyunClient.doAction(request);
            return;
        }

        if (records.size() > 1) {
            throw new BaseException(String.format("解析记录超过1条，暂时不支持更新: %s", domainRecord.getFullDomain()));
        }

        UpdateZoneRecordRequest request = new UpdateZoneRecordRequest();
        request.setRecordId(records.get(0).getRecordId());
        request.setValue(domainRecord.getValue());
        request.setRr(domainRecord.getRr());
        request.setType(domainRecord.getRecordType().getValue());
        aliyunClient.doAction(request);
    }

    /**
     * 删除解析记录
     * @param domainRecord
     * @throws ClientException
     */
    public void delete(@NotNull DomainRecordDTO domainRecord) throws ClientException {
        List<DescribeZoneRecordsResponse.Record> records = list(domainRecord);
        if (CollectionUtils.isEmpty(records)) {
            return;
        }
        records.stream().map(DescribeZoneRecordsResponse.Record::getRecordId)
                .forEach(recordId ->{
                    DeleteZoneRecordRequest request = new DeleteZoneRecordRequest();
                    request.setRecordId(recordId);
                    try {
                        aliyunClient.doAction(request);
                    } catch (ClientException e) {
                        log.error(String.format("zoneName: %s", domainRecord.getFullDomain()), e);
                        throw new BaseException(String.format("删除解析记录异常: %s", domainRecord.getFullDomain()));
                    }
                });
    }

    /**
     * 设置解析记录状态
     * @param domainRecord
     * @param statusEnum
     * @throws ClientException
     */
    public void setStatus(@NotNull DomainRecordDTO domainRecord, RecordStatusEnum statusEnum) throws ClientException {
        List<DescribeZoneRecordsResponse.Record> records = list(domainRecord);
        if (CollectionUtils.isEmpty(records)) {
            throw new BaseException(String.format("解析记录不存在: %s", domainRecord.getFullDomain()));
        }
        records.stream().map(DescribeZoneRecordsResponse.Record::getRecordId)
                .forEach(recordId ->{
                    SetZoneRecordStatusRequest request = new SetZoneRecordStatusRequest();
                    request.setRecordId(recordId);
                    request.setStatus(statusEnum.getAliPrivateZone());
                    try {
                        aliyunClient.doAction(request);
                    } catch (ClientException e) {
                        log.error(String.format("zoneName: %s", domainRecord.getFullDomain()), e);
                        throw new BaseException(String.format("设置解析记录状态异常: %s", domainRecord.getFullDomain()));
                    }
                });
    }

    public PrivateZoneRecordUtil(AliyunClient aliyunClient) {
        this.aliyunClient = aliyunClient;
    }

    private String getZoneId(String zoneName) {
        List<DescribeZonesResponse.Zone> zoneList = privateZoneList(zoneName);
        if (CollectionUtils.isEmpty(zoneList)|| zoneList.size() != 1) {
            log.error("zoneList:{}", JsonUtil.toJSONString(zoneList));
            throw new BaseException(String.format("获取zoneId异常: %s", zoneName));
        }
        return zoneList.get(0).getZoneId();
    }

    private List<DescribeZonesResponse.Zone> privateZoneList(String zoneName) {
        try {
            DescribeZonesRequest pvtzZoneListRequest = new DescribeZonesRequest();
            pvtzZoneListRequest.setPageSize(10);
            pvtzZoneListRequest.setKeyword(zoneName);
            DescribeZonesResponse pvtzZoneListResponse = aliyunClient.getResponse(pvtzZoneListRequest);
            return pvtzZoneListResponse.getZones();
        } catch (Exception e) {
            log.error(String.format("zoneName: %s", zoneName), e);
            return Lists.newArrayList();
        }
    }

}
