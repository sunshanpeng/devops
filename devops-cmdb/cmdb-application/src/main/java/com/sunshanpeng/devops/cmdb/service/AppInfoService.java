package com.sunshanpeng.devops.cmdb.service;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.common.base.BaseService;

public interface AppInfoService extends BaseService<AppInfoEntity, Long>{
    void save(ApplicationDetailDTO application);
}
