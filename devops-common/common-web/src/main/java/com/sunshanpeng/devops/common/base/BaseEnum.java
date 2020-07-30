package com.sunshanpeng.devops.common.base;

import java.util.List;

public interface BaseEnum<T extends BaseEnum<T>> {
    Object getValue();

    Object getLabel();

    List<ValueLabelDTO> getList();
}
