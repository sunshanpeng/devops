package com.sunshanpeng.devops.member.service.impl;

import com.sunshanepng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.domain.entity.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberEntity,Long, MemberRepository> implements MemberService{

}
