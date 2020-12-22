package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.core.base.BaseEnum;
import com.sunshanpeng.devops.common.core.base.Dict;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Dict(key = "monitorType", desc = "监控类型")
public enum MonitorTypeEnum implements BaseEnum {

    LIVENESS("liveness"),
    READINESS("readiness"),
    PROMETHEUS("prometheus"),
    ;
    MonitorTypeEnum(String value) {
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

    public static List<ValueLabelDTO> getList() {
        return Arrays.stream(MonitorTypeEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
