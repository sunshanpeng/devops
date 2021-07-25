package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService extends BaseService<MemberEntity> {

    List<SimpleMemberDTO> search(String keyword);

    Optional<MemberEntity> get(String username);

    SimpleMemberDTO userInfo(String username);

}
