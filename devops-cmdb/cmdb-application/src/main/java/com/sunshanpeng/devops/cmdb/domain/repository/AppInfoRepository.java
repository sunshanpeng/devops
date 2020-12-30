package com.sunshanpeng.devops.cmdb.domain.repository;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.common.base.BaseRepository;
import java.util.Optional;

public interface AppInfoRepository extends BaseRepository<AppInfoEntity> {
    Optional<AppInfoEntity> findByAppName(String appName);
}
