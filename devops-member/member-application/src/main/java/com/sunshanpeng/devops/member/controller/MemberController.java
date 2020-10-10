package com.sunshanpeng.devops.member.controller;

import com.sunshanpeng.devops.common.base.BaseController;
import com.sunshanpeng.devops.common.base.BasePageResponse;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.MemberPageQueryDTO;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;
import com.sunshanpeng.devops.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "成员管理")
@RestController
@RequestMapping("/members")
public class MemberController extends BaseController<MemberEntity, Long, MemberService> {

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页查询用户")
    public BasePageResponse<MemberEntity> query(MemberPageQueryDTO queryDTO) {
        Page<MemberEntity> page = baseService.pageQuery(queryDTO);
        return BasePageResponse.createSuccessResult(page.getContent(), queryDTO.getPageIndex(),
                queryDTO.getPageSize(), page.getTotalElements());
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索用户", notes = "关键字搜索")
    public BaseResponse<List<SimpleMemberDTO>> search(@RequestParam String keyword) {
        List<SimpleMemberDTO> list = baseService.search(keyword);
        return BaseResponse.createSuccessResult(list);
    }
}
