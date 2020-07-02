package com.sunshanepng.devops.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int MAX_PAGE_SIZE = 1000;

    private int pageIndex = 1;

    private int pageSize = 20;

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }
}
