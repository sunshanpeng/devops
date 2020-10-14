package com.sunshanpeng.devops.huaweicloud.http;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {
    private Integer statusCode;

    private String content;
}
