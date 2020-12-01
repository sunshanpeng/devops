package com.sunshanpeng.devops.common.core.thread;

import lombok.Data;

@Data
public class ThreadPoolProperties {
    /**
     * 核心线程数
     */
    Integer corePoolSize = 4;

    /**
     * 最大线程池数量
     */
    Integer maxPoolSize = 100;

    /**
     * 队列最大长度
     */
    Integer maxQueueSize = 5000;

    Long keepAliveSeconds = 60L;
}
