package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum AppUserTypeEnum implements BaseEnum {
    PRIMARY("primary", "主要负责人"),
    SECONDARY("secondary", "次要负责人"),
    ;
    AppUserTypeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
    private String value;
    private String label;

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getLabel() {
        return this.label;
    }

    @Override
    public List<ValueLabelDTO> getList() {
        return Arrays.stream(AppUserTypeEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}