package com.sunshanepng.devops.common.base;

import java.util.List;

public interface BaseEnum<T extends BaseEnum<T>> {
    Object getValue();

    Object getText();

    List<ValueLabelDTO> getList();
}
