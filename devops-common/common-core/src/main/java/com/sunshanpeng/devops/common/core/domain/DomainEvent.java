package com.sunshanpeng.devops.common.core.domain;

import java.time.LocalDateTime;

public class DomainEvent<T> {
    private String id;
    private LocalDateTime time;
    private Object source;
    private T data;
}
