package com.sunshanpeng.devops.member.service.impl;

import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.member.domain.dao.OrganizationRepository;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.dto.OrgPageQueryDTO;
import com.sunshanpeng.devops.member.service.OrganizationService;
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

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationEntity, Long, OrganizationRepository> implements OrganizationService {

    @Override
    public Page<OrganizationEntity> pageQuery(OrgPageQueryDTO queryDTO) {
        Specification<OrganizationEntity> specification = (Specification<OrganizationEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(queryDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name"), "%" + queryDTO.getName() + "%"));
            }
            if (StringUtils.isNotBlank(queryDTO.getParentId())) {
                list.add(criteriaBuilder.equal(root.get("parentId"), queryDTO.getParentId()));
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
}
