package com.sunshanpeng.devops.cmdb.service;

import com.sunshanpeng.devops.cmdb.domain.entity.ClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.EnvClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.EnvEntity;
import com.sunshanpeng.devops.common.base.BaseService;

import java.util.List;

public interface EnvClusterService extends BaseService<EnvClusterEntity, Long> {

    EnvEntity save(EnvEntity envEntity);

    ClusterEntity save(ClusterEntity entity);

    List<EnvEntity> findAllEnvs();

    List<ClusterEntity> findAllClusters();
}
