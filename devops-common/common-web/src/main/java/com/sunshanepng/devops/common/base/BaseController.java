package com.sunshanepng.devops.common.base;

import com.sunshanepng.devops.common.exception.BusinessException;
import com.sunshanepng.devops.common.log.MethodLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseController<T extends BaseEntity, ID, S extends BaseService<T, ID>> {
    @Resource
    protected S baseService;

    @GetMapping("")
    @ApiOperation(value = "查询所有记录", notes = "不分页查询所有记录")
    public BaseResponse<List<T>> get() {
        return BaseResponse.createSuccessResult(baseService.findAll());
    }

    @GetMapping("/{id}")
    @MethodLogger
    @ApiOperation(value = "查询", notes = "通过主键ID查询")
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    public BaseResponse<T> get(@PathVariable ID id) {
        T result = baseService.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("id of %s not found", id)));
        return BaseResponse.createSuccessResult(result);
    }

    @DeleteMapping("/{id}")
    @MethodLogger
    @ApiOperation(value = "删除", notes = "通过ID删除")
    @ApiImplicitParam(name = "id", value = "ID")
    public BaseResponse<Void> delete(@PathVariable ID id) {
        baseService.deleteById(id);
        return BaseResponse.createSuccessResult(null);
    }

    @PostMapping
    @MethodLogger
    @ApiOperation(value = "添加")
    public BaseResponse<T> post(@RequestBody @Validated T user) {
        T model = baseService.save(user);
        return BaseResponse.createSuccessResult(model);
    }


    @PutMapping("/{id}")
    @MethodLogger
    @ApiOperation(value = "修改")
    public BaseResponse<T> put(@PathVariable ID id, @RequestBody T entity) {
        if (!id.equals(entity.getId())) {
            throw new BusinessException("The Id of path variable is different from request body");
        }
        T model = baseService.update(entity);
        return BaseResponse.createSuccessResult(model);
    }
}
