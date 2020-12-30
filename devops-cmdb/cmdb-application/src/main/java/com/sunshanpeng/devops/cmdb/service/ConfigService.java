package com.sunshanpeng.devops.cmdb.service;

import com.sunshanpeng.devops.cmdb.domain.entity.MetaConfigEntity;
import com.sunshanpeng.devops.common.base.BaseService;
import com.sunshanpeng.devops.common.core.dto.KeyValueDTO;

import java.util.List;

public interface ConfigService extends BaseService<MetaConfigEntity> {

    List<KeyValueDTO> getConfigs(String group);

    String getValue(String key);
}
