package com.sunshanpeng.devops.member.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "成员")
@TableName(MemberEntity.TABLE_NAME)
public class MemberEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_member";

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank
    private String fullName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password = "123456";//暂时没用，后期处理

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull
    private Long phone;

    @ApiModelProperty(hidden = true)
    @Builder.Default
    protected Boolean isDeleted = false;
}
