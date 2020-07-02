package com.sunshanepng.devops.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
}
