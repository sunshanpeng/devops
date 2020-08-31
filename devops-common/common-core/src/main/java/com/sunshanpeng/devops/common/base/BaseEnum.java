package com.sunshanpeng.devops.common.base;

import java.util.List;

public interface BaseEnum {
    Object getValue();

    Object getLabel();

    List<ValueLabelDTO> getList();
}
