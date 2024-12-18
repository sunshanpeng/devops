package com.sunshanpeng.devops.cmdb.service;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.common.base.BaseService;

import java.util.Optional;

public interface AppInfoService extends BaseService<AppInfoEntity>{
    void save(ApplicationDetailDTO application);

    Optional<ApplicationDTO> findByAppName(String appName);

}
