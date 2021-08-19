package com.sunshanpeng.devops.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageResponse<T> extends BaseResponse<List<T>> {
    private static final long serialVersionUID = 1L;

    private int pageIndex;
    private long totalCount;
    private int pageSize;

    public static <T> BasePageResponse<T> createSuccessResult(List<T> data, int pageIndex, int pageSize, long totalCount) {
        BasePageResponse<T> response = new BasePageResponse<>();
        response.setData(data);
        response.setSuccess(true);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        return response;
    }

}
