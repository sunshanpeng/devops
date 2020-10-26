package com.sunshanpeng.devops.common.core.base;

import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;

import java.util.List;

public interface BaseEnum {
    Object getValue();

    Object getLabel();

    List<ValueLabelDTO> getList();
}
