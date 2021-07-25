package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.AppUserEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.AppInfoRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.AppUserRepository;
import com.sunshanpeng.devops.cmdb.dto.AppUserDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.cmdb.enums.AppUserTypeEnum;
import com.sunshanpeng.devops.cmdb.service.AppInfoService;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.core.util.BeanUtil;
import com.sunshanpeng.devops.common.exception.BusinessException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfoEntity, AppInfoRepository> implements AppInfoService {

    @Resource
    private AppUserRepository appUserRepository;
    @Resource
    private ApplicationEventPublisher publisher;

    @Override
    public void save(ApplicationDetailDTO application) {
        beforeSave(application);

        doSave(application);

        afterSave(application);
    }

    @Override
    public Optional<ApplicationDTO> findByAppName(String appName) {
        Optional<AppInfoEntity> appInfoEntity = baseRepository.findByAppName(appName);
        if (!appInfoEntity.isPresent()) {
            return Optional.empty();
        }
        ApplicationDTO applicationDTO = converter(appInfoEntity.get());
        return Optional.ofNullable(applicationDTO);
    }

    private void beforeSave(ApplicationDetailDTO application) {
        // validate
        if (baseRepository.findByAppName(application.getAppName()).isPresent()) {
            throw new BusinessException(String.format("应用名%s已存在", application.getAppName()));
        }
    }

    private void doSave(ApplicationDetailDTO application) {
        AppInfoEntity appInfoEntity = BeanUtil.copy(application, AppInfoEntity.class);
        baseRepository.insert(appInfoEntity);
    }

    private void afterSave(ApplicationDetailDTO application) {
        // notice
    }

    private ApplicationDTO converter(AppInfoEntity appInfoEntity) {
        if (appInfoEntity == null) {
            return null;
        }
        ApplicationDTO applicationDTO = BeanUtil.copy(appInfoEntity, ApplicationDTO.class);
        List<AppUserEntity> appUserEntityList = appUserRepository.findByAppName(appInfoEntity.getAppName());
        List<AppUserEntity> owner = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.OWNER.getValue().equals(appUserEntity.getAppUserType()))
                .collect(Collectors.toList());
        List<AppUserEntity> developer = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.DEVELOPER.getValue().equals(appUserEntity.getAppUserType()))
                .collect(Collectors.toList());
        List<AppUserEntity> qa = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.QA.getValue().equals(appUserEntity.getAppUserType()))
                .collect(Collectors.toList());

        List<AppUserDTO> ownerUsers = owner.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setDeveloper(ownerUsers);

        List<AppUserDTO> developerUsers = developer.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setDeveloper(developerUsers);

        List<AppUserDTO> qaUsers = qa.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setQa(qaUsers);
        return applicationDTO;
    }
}
