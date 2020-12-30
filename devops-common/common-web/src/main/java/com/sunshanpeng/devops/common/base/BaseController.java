package com.sunshanpeng.devops.common.base;

import com.sunshanpeng.devops.common.exception.BusinessException;
import com.sunshanpeng.devops.common.log.MethodLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S baseService;

    @GetMapping("/{id}")
    @ApiOperation(value = "查询", notes = "通过主键ID查询")
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    public BaseResponse<T> get(@PathVariable Long id) {
        T result = baseService.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("id of %s not found", id)));
        return BaseResponse.createSuccessResult(result);
    }

    @DeleteMapping("/{id}")
    @MethodLogger
    @ApiOperation(value = "删除", notes = "通过ID删除")
    @ApiImplicitParam(name = "id", value = "ID")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        baseService.deleteById(id);
        return BaseResponse.createSuccessResult(null);
    }

    @PostMapping
    @MethodLogger
    @ApiOperation(value = "添加")
    public BaseResponse<T> post(@RequestBody @Validated T entity) {
        T model = baseService.save(entity);
        return BaseResponse.createSuccessResult(model);
    }


    @PutMapping("/{id}")
    @MethodLogger
    @ApiOperation(value = "修改")
    public BaseResponse<T> put(@PathVariable Long id, @RequestBody T entity) {
        if (!id.equals(entity.getId())) {
            throw new BusinessException("The Id of path variable is different from request body");
        }
        T model = baseService.update(entity);
        return BaseResponse.createSuccessResult(model);
    }
}
