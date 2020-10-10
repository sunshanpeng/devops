package com.sunshanpeng.devops.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ValueLabelDTO {
    private Object value;

    private Object label;
}
