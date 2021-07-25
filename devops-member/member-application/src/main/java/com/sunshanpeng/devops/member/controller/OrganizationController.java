package com.sunshanpeng.devops.member.controller;

import com.sunshanpeng.devops.common.base.BaseController;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.dto.TreeDTO;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "组织管理")
@RestController
@RequestMapping("/orgs")
public class OrganizationController extends BaseController<OrganizationEntity, OrganizationService> {

    @Resource
    private OrganizationService organizationService;

    @GetMapping("/tree")
    @ApiOperation(value = "组织树", notes = "查询整个组织树或指定根下组织")
    public BaseResponse<List<TreeDTO>> tree(Long rootId) {
        return BaseResponse.createSuccessResult(organizationService.tree(rootId));
    }
}
