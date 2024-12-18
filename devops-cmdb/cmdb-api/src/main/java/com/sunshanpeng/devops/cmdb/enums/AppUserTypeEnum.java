package com.sunshanpeng.devops.cmdb.enums;

import com.sunshanpeng.devops.common.core.base.BaseEnum;
import com.sunshanpeng.devops.common.core.base.Dict;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Dict(key = "appUserType", desc = "应用负责人类型")
public enum AppUserTypeEnum implements BaseEnum {
    OWNER("owner", "负责人"),
    DEVELOPER("developer", "开发"),
    QA("qa", "测试"),
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

    public static List<ValueLabelDTO> getList() {
        return Arrays.stream(AppUserTypeEnum.values()).map(e ->
                ValueLabelDTO.builder().value(e.getValue())
                        .label(e.getLabel()).build())
                .collect(Collectors.toList());
    }
}
