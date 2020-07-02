package com.sunshanepng.devops.common.base;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    @Resource
    protected R baseRepository;

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            baseRepository.deleteById(id);
        }
    }

    @Override
    public T update(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

}
