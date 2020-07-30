package com.sunshanpeng.devops.common.base;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface BaseService<T,ID> {
    T save(@NotNull T entity);

    void deleteById(@NotNull ID id);

    T update(@NotNull T entity);

    Optional<T> findById(@NotNull ID id);

    List<T> findAll();
}
