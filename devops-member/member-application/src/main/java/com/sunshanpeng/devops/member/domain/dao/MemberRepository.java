package com.sunshanpeng.devops.member.domain.dao;

import com.sunshanpeng.devops.common.base.BaseRepository;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends BaseRepository<MemberEntity> {

    List<MemberEntity> findAllByUsernameLikeOrFullNameLike(String username, String fullName);

    Optional<MemberEntity> findByUsername(String username);
}
