package com.sunshanpeng.devops.resource.dto;

import com.sunshanpeng.devops.resource.enums.RecordTypeEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

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
     * 全域名，rr + domainName
     */
    private String fullDomain;
    /**
     * 主机记录值
     */
    private String value;

    /**
     * 记录类型
     */
    private RecordTypeEnum recordType;

    public String getFullDomain() {
        Assert.notNull(rr, "rr must not be null");
        Assert.notNull(domainName, "domainName must not be null");
        return rr + "." + domainName;
    }
}
