package com.sunshanpeng.devops.member.controller;

import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;
import com.sunshanpeng.devops.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "成员管理")
@RestController
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/members/search")
    @ApiOperation(value = "搜索用户", notes = "关键字搜索")
    public BaseResponse<List<SimpleMemberDTO>> search(@RequestParam String keyword) {
        List<SimpleMemberDTO> list = memberService.search(keyword);
        return BaseResponse.createSuccessResult(list);
    }

    @GetMapping("/members/user")
    @ApiOperation(value = "用户信息", notes = "根据用户名获取用户信息")
    public BaseResponse<SimpleMemberDTO> userInfo(@RequestHeader String username) {
        SimpleMemberDTO simpleMemberDTO = memberService.userInfo(username);
        return BaseResponse.createSuccessResult(simpleMemberDTO);
    }
}
