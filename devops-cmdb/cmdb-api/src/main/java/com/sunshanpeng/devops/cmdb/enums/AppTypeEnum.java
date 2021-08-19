package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.core.base.BaseEnum;
import com.sunshanpeng.devops.common.core.base.Dict;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Dict(key = "appType", desc = "应用类型")
public enum AppTypeEnum implements BaseEnum {

    JAVA("JAVA", "JavaWeb应用"),
    VUE("VUE", "前端Vue应用"),
    DATABASE("DATABASE", "数据存储"),
    OUTSOURCE("OUTSOURCE", "非自研应用"),
    ;

    AppTypeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    private String value;
    private String label;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object getLabel() {
        return value;
    }

    public static List<ValueLabelDTO> getList() {
        return Arrays.stream(AppTypeEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
