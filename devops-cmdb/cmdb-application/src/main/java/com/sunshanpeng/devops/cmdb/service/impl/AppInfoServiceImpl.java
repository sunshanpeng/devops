package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.AppUserEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.CiInfoEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.MonitorEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.AppInfoRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.AppUserRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.CiInfoRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.MonitorRepository;
import com.sunshanpeng.devops.cmdb.dto.*;
import com.sunshanpeng.devops.cmdb.enums.AppUserTypeEnum;
import com.sunshanpeng.devops.cmdb.enums.MonitorTypeEnum;
import com.sunshanpeng.devops.cmdb.service.AppInfoService;
import com.sunshanpeng.devops.common.base.BasePageResponse;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.exception.BusinessException;
import com.sunshanpeng.devops.common.util.BeanUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfoEntity, Long, AppInfoRepository> implements AppInfoService {

    @Resource
    private AppUserRepository appUserRepository;
    @Resource
    private CiInfoRepository ciInfoRepository;
    @Resource
    private MonitorRepository monitorRepository;
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

    @Override
    public Optional<ApplicationDetailDTO> getDetail(String appName) {
        Optional<ApplicationDTO> applicationDTOOptional = findByAppName(appName);
        if (!applicationDTOOptional.isPresent()) {
            return Optional.empty();
        }
        ApplicationDetailDTO detailDTO = BeanUtil.copy(applicationDTOOptional.get(), ApplicationDetailDTO.class);
        Optional<CiInfoEntity> ciInfoEntityOptional = ciInfoRepository.findByAppName(appName);
        ciInfoEntityOptional.ifPresent(ciInfoEntity -> {
            detailDTO.setCodeUrl(ciInfoEntity.getCodeUrl());
            detailDTO.setArtifactPath(ciInfoEntity.getArtifactPath());
        });

        List<MonitorEntity> monitors = monitorRepository.findByAppName(appName);
        monitors.stream().filter(monitor -> MonitorTypeEnum.READINESS.getValue().equals(monitor.getMonitorType()))
                .findFirst().ifPresent(monitor -> detailDTO.setReadiness(new MonitorDTO(monitor.getMonitorPort(), monitor.getMonitorPath())));
        monitors.stream().filter(monitor -> MonitorTypeEnum.LIVENESS.getValue().equals(monitor.getMonitorType()))
                .findFirst().ifPresent(monitor -> detailDTO.setLiveness(new MonitorDTO(monitor.getMonitorPort(), monitor.getMonitorPath())));
        List<MonitorDTO> monitorList = monitors.stream().filter(monitor -> MonitorTypeEnum.PROMETHEUS.getValue().equals(monitor.getMonitorType()))
                .map(monitor -> new MonitorDTO(monitor.getMonitorPort(), monitor.getMonitorPath())).collect(Collectors.toList());
        detailDTO.setPrometheus(monitorList);
        return Optional.of(detailDTO);
    }

    @Override
    public BasePageResponse<ApplicationDTO> pageQuery(ApplicationPageQueryDTO queryDTO) {
        //查询应用
        Specification<AppInfoEntity> specification = (Specification<AppInfoEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(queryDTO.getAppName())) {
                list.add(criteriaBuilder.equal(root.get("appName"), queryDTO.getAppName()));
            }
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicates);
        };
        Pageable pageable = PageRequest.of(queryDTO.getPageIndex(),
                queryDTO.getPageSize(), Sort.Direction.DESC, "id")
                //分页插件下标从0开始，第1页下标为0
                .previous();
        Page<AppInfoEntity> page = baseRepository.findAll(specification, pageable);
        //查询应用对应负责人
        List<ApplicationDTO> applicationDTOList = page.getContent().stream()
                .map(this::converter).collect(Collectors.toList());
        //返回
        return BasePageResponse.createSuccessResult(applicationDTOList, queryDTO.getPageIndex(),
                queryDTO.getPageSize(), page.getTotalElements());
    }

    private void beforeSave(ApplicationDetailDTO application) {
        // validate
        if (baseRepository.findByAppName(application.getAppName()).isPresent()) {
            throw new BusinessException(String.format("应用名%s已存在",application.getAppName()));
        }
    }

    private void doSave(ApplicationDetailDTO application) {
        AppInfoEntity appInfoEntity = BeanUtil.copy(application, AppInfoEntity.class);
        baseRepository.save(appInfoEntity);
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
        Optional<AppUserEntity> primary = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.PRIMARY.getValue().equals(appUserEntity.getAppUserType()))
                .findFirst();
        List<AppUserEntity> secondary = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.SECONDARY.getValue().equals(appUserEntity.getAppUserType()))
                .collect(Collectors.toList());
        List<AppUserEntity> qa = appUserEntityList.stream().filter(appUserEntity ->
                AppUserTypeEnum.QA.getValue().equals(appUserEntity.getAppUserType()))
                .collect(Collectors.toList());
        primary.ifPresent(user -> applicationDTO.setPrimary(user.getUsername(), user.getFullName()));
        List<AppUserDTO> secondaryUsers = secondary.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setSecondary(secondaryUsers);
        List<AppUserDTO> qaUsers = qa.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setQa(qaUsers);
        return applicationDTO;
    }
}
