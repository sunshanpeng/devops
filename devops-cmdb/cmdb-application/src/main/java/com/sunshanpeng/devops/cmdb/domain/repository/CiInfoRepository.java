package com.sunshanpeng.devops.cmdb.domain.repository;

import com.sunshanpeng.devops.cmdb.domain.entity.CiInfoEntity;
import com.sunshanpeng.devops.common.base.BaseRepository;

import java.util.Optional;

public interface CiInfoRepository extends BaseRepository<CiInfoEntity> {

    Optional<CiInfoEntity> findByAppName(String appName);
}
