package com.sunshanpeng.devops.member.service.impl;

import com.sunshanepng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.member.domain.dao.OrganizationRepository;
import com.sunshanpeng.devops.member.domain.entity.OrganizationEntity;
import com.sunshanpeng.devops.member.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationEntity, Long, OrganizationRepository> implements OrganizationService {

}
