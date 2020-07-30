package com.sunshanpeng.devops.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(hidden = true)
    @Column(name = "create_time", nullable = false, columnDefinition = "datetime not null default now() comment '创建时间'")
    private LocalDateTime createTime;

    @ApiModelProperty(hidden = true)
    @Column(name = "modify_time", nullable = false, columnDefinition = "datetime not null default now() comment '修改时间'")
    private LocalDateTime modifyTime;

    @PrePersist
    protected void prePersist() {
        this.setCreateTime(LocalDateTime.now());
        this.setModifyTime(LocalDateTime.now());
    }

    @PreUpdate
    protected void preUpdate() {
        this.setModifyTime(LocalDateTime.now());
    }
}
