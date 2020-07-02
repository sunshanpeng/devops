package com.sunshanpeng.devops.member.controller;

import com.sunshanepng.devops.common.base.BaseController;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.service.impl.MemberService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "成员管理")
@RestController
@RequestMapping("/members")
public class MemberController extends BaseController<MemberEntity, Long, MemberService> {

}
