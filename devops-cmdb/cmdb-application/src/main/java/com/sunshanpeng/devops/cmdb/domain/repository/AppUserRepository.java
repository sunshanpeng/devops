package com.sunshanpeng.devops.cmdb.domain.repository;

import com.sunshanpeng.devops.cmdb.domain.entity.AppUserEntity;
import com.sunshanpeng.devops.common.base.BaseRepository;

import java.util.List;

public interface AppUserRepository extends BaseRepository<AppUserEntity> {

    List<AppUserEntity> findByAppName(String appName);

}
