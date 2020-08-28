package com.sunshanpeng.devops.common.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValueLabelDTO {
    private Object value;

    private Object label;
}
