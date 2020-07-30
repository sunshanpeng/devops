package com.sunshanpeng.devops.member.controller;

import com.sunshanpeng.devops.common.base.BaseController;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.service.OrganizationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "组织管理")
@RestController
@RequestMapping("/organizations")
public class OrganizationController extends BaseController<OrganizationEntity, Long, OrganizationService> {

}
