package com.sunshanpeng.devops.member.service;

import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.common.dto.TreeDTO;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;

import java.util.List;

public interface OrganizationService extends BaseService<OrganizationEntity> {

    List<TreeDTO> tree(Long rootId);
}
