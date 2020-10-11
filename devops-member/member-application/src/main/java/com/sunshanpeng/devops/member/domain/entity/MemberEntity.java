package com.sunshanpeng.devops.member.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import com.sunshanpeng.devops.common.consts.DatabaseConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = MemberEntity.TABLE_NAME)
@SQLDelete(sql = "update " + MemberEntity.TABLE_NAME + " set " + DatabaseConst.DELETE_FIELD + " = 1 where id = ?")
@Where(clause = DatabaseConst.DELETE_WHERE)
@ApiModel(description = "成员")
public class MemberEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_member";

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    @Column(nullable = false)
    private String password = "123456";//暂时没用，后期处理

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull
    @Column(nullable = false)
    private Long phone;

    @ApiModelProperty(hidden = true)
    @Column(name =  DatabaseConst.DELETE_FIELD, columnDefinition = "tinyint default '0'")
    protected Boolean isDeleted = false;
}
