package com.sunshanpeng.devops.member.service.impl;

import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.dto.TreeDTO;
import com.sunshanpeng.devops.common.utils.TreeUtil;
import com.sunshanpeng.devops.member.domain.dao.OrganizationRepository;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationEntity, OrganizationRepository> implements OrganizationService {

    private static final Long DEFAULT_ROOT_ID = 1L;

    @Override
    public List<TreeDTO> tree(Long rootId) {
        if (rootId == null) {
            rootId = DEFAULT_ROOT_ID;
        }
        Optional<OrganizationEntity> entityOptional = Optional.ofNullable(baseRepository.selectById(rootId));
        if (!entityOptional.isPresent()) {
            return Collections.emptyList();
        }
        OrganizationEntity parenOrg = entityOptional.get();
        TreeDTO rootNode = covert(parenOrg);
        List<TreeDTO> treeOrgs = baseRepository.selectList(null)
                .stream().map(this::covert)
                .collect(Collectors.toList());
        return TreeUtil.getAllChildrens(rootNode, treeOrgs);
    }

    private TreeDTO covert(OrganizationEntity org) {
        return TreeDTO.builder().parentId(org.getParentId())
                .label(org.getName())
                .id(org.getId())
                .value(org.getId())
                .build();
    }
}
