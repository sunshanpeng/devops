package com.sunshanpeng.devops.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T>> implements BaseService<T> {

    @Autowired
    protected R baseRepository;

    @Override
    public T save(T entity) {
        baseRepository.insert(entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            baseRepository.deleteById(id);
        }
    }

    @Override
    public T update(T entity) {
        baseRepository.updateById(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(baseRepository.selectById(id));
    }

    @Override
    public List<T> findAll() {
        return baseRepository.selectList(null);
    }

}
