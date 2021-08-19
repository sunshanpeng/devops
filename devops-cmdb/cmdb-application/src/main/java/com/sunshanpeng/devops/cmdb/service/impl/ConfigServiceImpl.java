package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.MetaConfigEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.MetaConfigRepository;
import com.sunshanpeng.devops.cmdb.service.ConfigService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.core.dto.KeyValueDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<MetaConfigEntity, MetaConfigRepository> implements ConfigService {

    @Resource
    private MetaConfigRepository configRepository;
    @Override
    public List<KeyValueDTO> getConfigs(String group) {
        return configRepository.findByGroup(group).stream()
                .map(config -> new KeyValueDTO(config.getKey(), config.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getValue(String key) {
        MetaConfigEntity config = configRepository.findByKey(key);
        return Optional.ofNullable(config).map(MetaConfigEntity::getValue).orElse("");
    }
}
