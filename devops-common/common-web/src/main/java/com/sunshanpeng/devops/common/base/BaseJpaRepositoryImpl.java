package com.sunshanpeng.devops.common.base;

import com.sunshanpeng.devops.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.util.ProxyUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class BaseJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @Autowired
    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * 通用save方法 ：新增/选择性更新
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(S entity) {
        if (this.entityInformation.isNew(entity)) {
            this.em.persist(entity);
            return entity;
        } else {
            Class<?> type = ProxyUtils.getUserClass(entity);
            T existing = (T) this.em.find(type, this.entityInformation.getId(entity));
            BeanUtil.copyNullProperties(existing, entity);
            return this.em.merge(entity);
        }
    }

}
