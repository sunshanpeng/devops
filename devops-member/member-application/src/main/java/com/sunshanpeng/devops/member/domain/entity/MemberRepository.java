package com.sunshanpeng.devops.member.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>, JpaSpecificationExecutor<MemberEntity> {

}
