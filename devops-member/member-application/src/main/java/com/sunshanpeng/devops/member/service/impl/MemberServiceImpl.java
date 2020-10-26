package com.sunshanpeng.devops.member.service.impl;

import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.core.util.BeanUtil;
import com.sunshanpeng.devops.member.domain.dao.MemberRepository;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.MemberPageQueryDTO;
import com.sunshanpeng.devops.member.dto.SimpleMemberDTO;
import com.sunshanpeng.devops.member.service.MemberService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberEntity, Long, MemberRepository> implements MemberService {

    @Override
    public Page<MemberEntity> pageQuery(MemberPageQueryDTO queryDTO) {
        Specification<MemberEntity> specification = (Specification<MemberEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(queryDTO.getUsername())) {
                list.add(criteriaBuilder.equal(root.get("username"), queryDTO.getUsername()));
            }
            if (StringUtils.isNotBlank(queryDTO.getPhone())) {
                list.add(criteriaBuilder.equal(root.get("phone"), queryDTO.getPhone()));
            }
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicates);
        };
        Pageable pageable = PageRequest.of(queryDTO.getPageIndex(),
                queryDTO.getPageSize(), Sort.Direction.DESC, "id")
                //分页插件下标从0开始，第1页下标为0
                .previous();
        return baseRepository.findAll(specification, pageable);
    }

    @Override
    public List<SimpleMemberDTO> search(String keyword) {
        keyword = "%" + keyword + "%";
        return baseRepository.findAllByUsernameLikeOrFullNameLike(keyword, keyword)
                .stream().map(memberEntity -> BeanUtil.copy(memberEntity, SimpleMemberDTO.class))
                .collect(Collectors.toList());
    }
}
