package com.sunshanpeng.devops.cmdb.service.impl;

import com.sunshanpeng.devops.cmdb.domain.entity.AppInfoEntity;
import com.sunshanpeng.devops.cmdb.domain.entity.AppUserEntity;
import com.sunshanpeng.devops.cmdb.domain.repository.AppInfoRepository;
import com.sunshanpeng.devops.cmdb.domain.repository.AppUserRepository;
import com.sunshanpeng.devops.cmdb.dto.AppUserDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationDetailDTO;
import com.sunshanpeng.devops.cmdb.dto.ApplicationPageQueryDTO;
import com.sunshanpeng.devops.cmdb.enums.AppUserTypeEnum;
import com.sunshanpeng.devops.cmdb.service.AppInfoService;
import com.sunshanpeng.devops.common.base.BasePageResponse;
import com.sunshanpeng.devops.common.base.BaseServiceImpl;
import com.sunshanpeng.devops.common.exception.BusinessException;
import com.sunshanpeng.devops.common.util.BeanUtil;
import io.micrometer.core.instrument.util.StringUtils;
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

    @Override
    public void save(ApplicationDetailDTO application) {
        beforeSave(application);

        doSave(application);

        afterSave(application);
    }

    @Override
    public ApplicationDTO findByAppName(String appName) {
        AppInfoEntity appInfoEntity = baseRepository.findByAppName(appName);
        return converter(appInfoEntity);
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
        if (baseRepository.findByAppName(application.getAppName()) != null) {
            throw new BusinessException(String.format("应用名%s已存在", application.getAppName()));
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
        primary.ifPresent(user -> applicationDTO.setPrimary(user.getAppName(), user.getFullName()));
        List<AppUserDTO> secondaryUsers = secondary.stream().map(appUserEntity ->
                new AppUserDTO(appUserEntity.getUsername(), appUserEntity.getFullName()))
                .collect(Collectors.toList());
        applicationDTO.setSecondary(secondaryUsers);
        return applicationDTO;
    }
}
