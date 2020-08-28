package com.sunshanpeng.devops.resource.dto;

import com.sunshanpeng.devops.resource.enums.RecordTypeEnum;
import lombok.Data;

@Data
public class DomainRecordDTO {
    /**
     * 主机记录
     */
    private String rr;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 主机记录值
     */
    private String value;

    /**
     * 记录类型
     */
    private RecordTypeEnum recordType;
}
