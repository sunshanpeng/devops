package com.sunshanpeng.devops.resource.dto;

import com.sunshanpeng.devops.resource.enums.RecordTypeEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class DomainRecordDTO {
    /**
     * 主机记录
     */
    private String rr;

    /**
     * 域名
     */
    @NotEmpty
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
