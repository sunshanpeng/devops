package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.MemberPageQueryDTO;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MemberService extends BaseService<MemberEntity> {

    Page<MemberEntity> pageQuery(@NotNull MemberPageQueryDTO queryDTO);

    List<SimpleMemberDTO> search(String keyword);

    Optional<MemberEntity> get(String username);

    SimpleMemberDTO userInfo(String username);

}
