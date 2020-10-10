package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.Dict;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Dict(key = "appStatus", desc = "应用状态")
public enum AppStatusEnum implements BaseEnum {

    ENABLE("enable", "启用"),
    DISABLE("disable", "停用"),
    ;

    AppStatusEnum(String value, String label) {
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
        return Arrays.stream(AppStatusEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
