package com.sunshanpeng.devops.member.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import com.sunshanpeng.devops.common.consts.DatabaseConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = OrganizationEntity.TABLE_NAME)
@SQLDelete(sql = "update " + OrganizationEntity.TABLE_NAME + " set " + DatabaseConst.DELETE_FIELD + " = 1 where id = ?")
@Where(clause = DatabaseConst.DELETE_WHERE)
@ApiModel(description = "组织")
public class OrganizationEntity extends BaseEntity {

    public static final String TABLE_NAME = "t_organization";

    @ApiModelProperty(value = "组织名称", required = true)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @ApiModelProperty(value = "上级组织", required = true)
    @NotBlank
    @Column(nullable = false)
    private String parentId;

    @ApiModelProperty(hidden = true)
    @Column(name =  DatabaseConst.DELETE_FIELD, nullable = false, columnDefinition = "tinyint default '0'")
    protected Boolean isDeleted = false;
}
