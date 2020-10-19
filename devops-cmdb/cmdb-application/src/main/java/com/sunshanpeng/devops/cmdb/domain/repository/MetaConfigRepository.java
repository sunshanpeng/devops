package com.sunshanpeng.devops.cmdb.domain.repository;

import com.sunshanpeng.devops.cmdb.domain.entity.MetaConfigEntity;
import com.sunshanpeng.devops.common.base.BaseRepository;

import java.util.List;

public interface MetaConfigRepository extends BaseRepository<MetaConfigEntity, Long> {
    List<MetaConfigEntity> findByGroup(String group);

    MetaConfigEntity findByKey(String key);
}
