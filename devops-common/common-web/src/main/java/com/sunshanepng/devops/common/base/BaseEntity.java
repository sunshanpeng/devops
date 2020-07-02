package com.sunshanepng.devops.common.base;

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
    private LocalDateTime createdTime;
    @ApiModelProperty(hidden = true)
    private LocalDateTime modifiedTime;

    @PrePersist
    protected void prePersist() {
        this.setCreatedTime(LocalDateTime.now());
        this.setModifiedTime(LocalDateTime.now());
    }

    @PreUpdate
    protected void preUpdate() {
        this.setModifiedTime(LocalDateTime.now());
    }
}
