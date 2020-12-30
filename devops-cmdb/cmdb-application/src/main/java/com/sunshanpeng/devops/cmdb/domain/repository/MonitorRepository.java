package com.sunshanpeng.devops.cmdb.domain.repository;

import com.sunshanpeng.devops.cmdb.domain.entity.MonitorEntity;
import com.sunshanpeng.devops.common.base.BaseRepository;

import java.util.List;

public interface MonitorRepository extends BaseRepository<MonitorEntity> {

    List<MonitorEntity> findByAppName(String appName);
}
