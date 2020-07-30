package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.MemberPageQueryDTO;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public interface MemberService extends BaseService<MemberEntity, Long> {

    Page<MemberEntity> pageQuery(@NotNull MemberPageQueryDTO queryDTO);

}
