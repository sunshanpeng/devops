package com.sunshanpeng.devops.cmdb.service;

import com.sunshanpeng.devops.cmdb.domain.entity.BusinessUnitEntity;
import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;

import java.util.List;

public interface BUService extends BaseService<BusinessUnitEntity> {

    List<ValueLabelDTO> getValueLabels();
}
