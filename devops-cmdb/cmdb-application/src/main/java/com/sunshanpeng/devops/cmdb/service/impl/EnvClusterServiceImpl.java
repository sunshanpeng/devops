package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.ClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.EnvClusterEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.ClusterRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.EnvClusterRepository;
import com.sunshanpeng.devops.cmdb.service.EnvClusterService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnvClusterServiceImpl extends BaseServiceImpl<EnvClusterEntity, EnvClusterRepository> implements EnvClusterService {
    @Resource
    private ClusterRepository clusterRepository;
    @Override
    public ClusterEntity save(ClusterEntity entity) {
        return clusterRepository.save(entity);
    }

    @Override
    public List<ClusterEntity> findAllClusters() {
        return clusterRepository.findAll();
    }




}
