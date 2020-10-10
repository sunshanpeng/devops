package com.sunshanpeng.devops.member.domain.dao;

import com.sunshanpeng.devops.common.base.BaseRepository;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;

import java.util.List;

public interface MemberRepository extends BaseRepository<MemberEntity, Long> {

    List<MemberEntity> findAllByUsernameLikeOrFullNameLike(String username, String fullName);

}
