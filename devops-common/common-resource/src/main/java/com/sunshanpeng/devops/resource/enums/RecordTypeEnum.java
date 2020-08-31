package com.sunshanpeng.devops.resource.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RecordTypeEnum implements BaseEnum {
    A("A", "A记录"),
    CNAME("CNAME", "CNAME记录");
    private String value;
    private String label;

    RecordTypeEnum(String value, String label) {
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
        return Arrays.stream(RecordTypeEnum.values()).map(recordTypeEnum ->
                ValueLabelDTO.builder().value(recordTypeEnum.getValue())
                        .label(recordTypeEnum.getLabel()).build())
                .collect(Collectors.toList());
    }
}
