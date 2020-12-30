package com.sunshanpeng.devops.common.base;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface BaseService<T> {
    T save(@NotNull T entity);

    void deleteById(@NotNull Long id);

    T update(@NotNull T entity);

    Optional<T> findById(@NotNull Long id);

    List<T> findAll();
}
