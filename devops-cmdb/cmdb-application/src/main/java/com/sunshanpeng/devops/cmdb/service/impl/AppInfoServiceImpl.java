package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.AppInfoRepository;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.cmdb.service.AppInfoService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.util.BeanUtil;
import org.springframework.stereotype.Service;

@Service
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfoEntity, Long, AppInfoRepository> implements AppInfoService {

    @Override
    public void save(ApplicationDetailDTO application) {
        beforeSave(application);

        doSave(application);

        afterSave(application);
    }

    private void beforeSave(ApplicationDetailDTO application) {
        // validate
    }

    private void doSave(ApplicationDetailDTO application) {
        AppInfoEntity appInfoEntity = BeanUtil.copy(application, AppInfoEntity.class);
        baseRepository.save(appInfoEntity);
    }

    private void afterSave(ApplicationDetailDTO application) {
        // notice
    }
}
