package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.Dict;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Dict(key = "appLevel", desc = "应用等级")
public enum AppLevelEnum implements BaseEnum {

    P1("P1","核心应用"),
    P2("P2","非核心应用"),
    ;

    AppLevelEnum(String value, String label) {
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
        return label;
    }

    @Override
    public List<ValueLabelDTO> getList() {
        return Arrays.stream(AppLevelEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
