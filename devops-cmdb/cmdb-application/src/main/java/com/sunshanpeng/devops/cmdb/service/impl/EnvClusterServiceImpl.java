package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.ClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.EnvClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.EnvEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.ClusterRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.EnvClusterRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.EnvRepository;
import com.sunshanpeng.devops.cmdb.service.EnvClusterService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnvClusterServiceImpl extends BaseServiceImpl<EnvClusterEntity,Long, EnvClusterRepository> implements EnvClusterService {

    @Resource
    private EnvRepository envRepository;
    @Resource
    private ClusterRepository clusterRepository;

    @Override
    public EnvEntity save(EnvEntity entity) {
        return envRepository.save(entity);
    }

    @Override
    public ClusterEntity save(ClusterEntity entity) {
        return clusterRepository.save(entity);
    }

    @Override
    public List<EnvEntity> findAllEnvs() {
        return envRepository.findAll();
    }

    @Override
    public List<ClusterEntity> findAllClusters() {
        return clusterRepository.findAll();
    }




}
