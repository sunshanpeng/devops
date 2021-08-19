package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.BusinessUnitEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.BURepository;
import com.sunshanpeng.devops.cmdb.service.BUService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BUServiceImpl extends BaseServiceImpl<BusinessUnitEntity, BURepository> implements BUService {

    @Override
    public List<ValueLabelDTO> getValueLabels() {
       return baseMapper.selectList(null).stream()
                .map(bu ->new ValueLabelDTO(bu.getBuCode(), bu.getBuName()))
             .collect(Collectors.toList());
    }
}
