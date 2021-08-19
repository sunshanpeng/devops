package com.sunshanpeng.devops.common.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T>> extends ServiceImpl<R, T> implements BaseService<T> {

}
