package com.sunshanpeng.devops.module.controller;

import com.sunshanpeng.devops.common.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "模块管理")
@RestController
@RequestMapping("/modules")
public class ModuleController {

    @GetMapping()
    @ApiOperation(value = "获取模块")
    public BaseResponse<String> get() {
        return BaseResponse.createSuccessResult("modules");
    }

}
