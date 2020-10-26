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

    JAVA("JAVA"),
    VUE("VUE"),
    PHP("PHP"),
    NODE_JS("NODE_JS"),
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
