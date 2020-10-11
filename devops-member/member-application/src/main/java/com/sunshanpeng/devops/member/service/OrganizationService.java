package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.dto.OrgPageQueryDTO;
import org.springframework.data.domain.Page;

public interface OrganizationService extends BaseService<OrganizationEntity, Long> {

    Page<OrganizationEntity> pageQuery(OrgPageQueryDTO queryDTO);
}
