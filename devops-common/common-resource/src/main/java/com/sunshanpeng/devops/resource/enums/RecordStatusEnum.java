package com.sunshanpeng.devops.resource.enums;

import com.sunshanpeng.devops.common.core.base.BaseEnum;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum RecordStatusEnum implements BaseEnum {
    ENABLE(1,"启用","Enable","ENABLE"),
    DISABLE(0,  "禁用","Disable","DISABLE"),;


    private Integer value;
    private String label;
    private String AliDNS;
    private String AliPrivateZone;

    RecordStatusEnum(Integer value, String label, String AliDNS, String AliPrivateZone) {
        this.value = value;
        this.label = label;
        this.AliDNS = AliDNS;
        this.AliPrivateZone = AliPrivateZone;
    }

    @Override
    public List<ValueLabelDTO> getList() {
        return Arrays.stream(RecordStatusEnum.values()).map(recordStatusEnum ->
                ValueLabelDTO.builder().value(recordStatusEnum.getValue())
                        .label(recordStatusEnum.getLabel()).build())
                .collect(Collectors.toList());
    }
}
