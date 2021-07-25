package com.sunshanpeng.devops.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.core.util.BeanUtil;
import com.sunshanpeng.devops.member.config.DevopsConfig;
import com.sunshanpeng.devops.member.domain.dao.MemberRepository;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;
import com.sunshanpeng.devops.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberEntity, MemberRepository> implements MemberService {

    @Resource
    private DevopsConfig config;

    @Override
    public List<SimpleMemberDTO> search(String keyword) {
        keyword = "%" + keyword + "%";
        return baseRepository.selectList(new LambdaQueryWrapper<MemberEntity>().like(MemberEntity::getUsername, keyword))
                .stream().map(memberEntity -> BeanUtil.copy(memberEntity, SimpleMemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberEntity> get(String username) {
        MemberEntity memberEntity = baseRepository.selectOne(new QueryWrapper<MemberEntity>().eq("username", username));
        return Optional.ofNullable(memberEntity);
    }

    @Override
    public SimpleMemberDTO userInfo(String username) {
        Optional<MemberEntity> memberEntityOptional = get(username);
        if (!memberEntityOptional.isPresent()) {
            return null;
        }
        SimpleMemberDTO memberDTO = BeanUtil.copy(memberEntityOptional.get(), SimpleMemberDTO.class);
        memberDTO.addRole("developer");
        if (config.isAdmin(memberDTO.getUsername())) {
            memberDTO.addRole("admin");
        }
        return memberDTO;
    }
}
