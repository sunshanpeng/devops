package com.sunshanpeng.devops.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int MAX_PAGE_SIZE = 1000;

    private Integer pageIndex = 1;

    private Integer pageSize = 20;

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex != null && pageIndex > 0) {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
            if (pageSize > MAX_PAGE_SIZE) {
                this.pageSize = MAX_PAGE_SIZE;
            }
        }
    }
}
