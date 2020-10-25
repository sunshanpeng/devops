package com.sunshanpeng.devops.cmdb.controller;

import com.sunshanpeng.devops.cmdb.dto.ApplicationDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationPageQueryDTO;
import com.sunshanpeng.devops.cmdb.service.AppInfoService;
import com.sunshanpeng.devops.common.base.BasePageResponse;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.log.MethodLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "应用管理")
@RestController
@RequestMapping("/apps")
public class AppController {

    @Resource
    private AppInfoService appInfoService;

    @PostMapping
    @MethodLogger
    @ApiOperation(value = "添加")
    public BaseResponse<Void> addApp(@RequestBody @Validated ApplicationDetailDTO application) {
        appInfoService.save(application);
        return BaseResponse.createSuccessResult(null);
    }

    @GetMapping("/{appName}")
    @ApiOperation(value = "查询", notes = "通过应用名称查询")
    public BaseResponse<ApplicationDTO> get(@PathVariable String appName) {
        return BaseResponse.createSuccessResult(appInfoService.findByAppName(appName).orElse(null));
    }


    @GetMapping("/{appName}/detail")
    @ApiOperation(value = "查询", notes = "通过应用名称查询应用详情")
    public BaseResponse<ApplicationDetailDTO> getDetail(@PathVariable String appName) {
        return BaseResponse.createSuccessResult(appInfoService.getDetail(appName).orElse(null));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页查询应用信息")
    public BasePageResponse<ApplicationDTO> page(ApplicationPageQueryDTO queryDTO) {
        return appInfoService.pageQuery(queryDTO);
    }
}
