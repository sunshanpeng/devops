package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.common.dto.TreeDTO;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.dto.OrgPageQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizationService extends BaseService<OrganizationEntity> {

    Page<OrganizationEntity> pageQuery(OrgPageQueryDTO queryDTO);

    List<TreeDTO> tree(Long rootId);
}
