package com.sunshanpeng.devops.member.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import com.sunshanpeng.devops.common.consts.DatabaseConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    @Column(nullable = false)
    private String password;

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @ApiModelProperty(value = "电话", required = true)
    @NotBlank
    @Length(min = 11, max = 11)
    @Column(nullable = false)
    private Long phone;

    @ApiModelProperty(hidden = true)
    @Column(name =  DatabaseConst.DELETE_FIELD, columnDefinition = "Bit default '0'")
    protected Boolean isDeleted = false;
}
