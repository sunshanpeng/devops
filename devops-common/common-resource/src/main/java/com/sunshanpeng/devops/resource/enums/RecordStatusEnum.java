package com.sunshanpeng.devops.resource.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RecordStatusEnum implements BaseEnum {
    ENABLE("Enable","启用"),
    DISABLE("Disable", "禁用"),;


    private String value;
    private String label;

    RecordStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public List<ValueLabelDTO> getList() {
        return Arrays.stream(RecordStatusEnum.values()).map(recordStatusEnum ->
                ValueLabelDTO.builder().value(recordStatusEnum.getValue())
                        .label(recordStatusEnum.getLabel()).build())
                .collect(Collectors.toList());
    }
}
