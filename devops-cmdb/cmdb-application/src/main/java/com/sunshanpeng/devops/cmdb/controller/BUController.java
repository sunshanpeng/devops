package com.sunshanpeng.devops.cmdb.controller;

import com.sunshanpeng.devops.cmdb.domain.entity.BusinessUnitEntity;
import com.sunshanpeng.devops.cmdb.service.BUService;
import com.sunshanpeng.devops.common.base.BaseController;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "业务线管理")
@RestController
@RequestMapping("/bus")
public class BUController extends BaseController<BusinessUnitEntity,BUService> {

    @GetMapping
    @ApiOperation(value = "业务线查询", notes = "获取所有业务线")
    public BaseResponse<List<ValueLabelDTO>> query() {
        List<ValueLabelDTO> valueLabels = baseService.getValueLabels();
        return BaseResponse.createSuccessResult(valueLabels);
    }
}
