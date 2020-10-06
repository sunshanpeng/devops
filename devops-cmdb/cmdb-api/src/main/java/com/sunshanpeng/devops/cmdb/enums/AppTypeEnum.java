package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum AppTypeEnum implements BaseEnum {

    SPRING_BOOT("springBoot"),
    JAVA_WEB("javaWeb"),
    VUE("vue"),
    PHP("php"),
    ;

    AppTypeEnum(String value) {
        this.value = value;
    }
    private String value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object getLabel() {
        return value;
    }

    @Override
    public List<ValueLabelDTO> getList() {
        return Arrays.stream(AppTypeEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
