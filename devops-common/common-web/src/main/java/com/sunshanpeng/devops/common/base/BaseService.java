package com.sunshanpeng.devops.common.base;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface BaseService<T> extends IService<T> {
}
