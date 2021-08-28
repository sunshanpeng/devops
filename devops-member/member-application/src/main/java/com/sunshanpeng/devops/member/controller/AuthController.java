package com.sunshanpeng.devops.member.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.core.util.MD5Util;
import com.sunshanpeng.devops.common.exception.ParamException;
import com.sunshanpeng.devops.member.domain.entity.MemberEntity;
import com.sunshanpeng.devops.member.dto.LoginDTO;
import com.sunshanpeng.devops.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Api(tags = "鉴权管理")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${auth.jwt.secret:123456}")
    private String secretKey;
    @Resource
    private MemberService memberService;

    private String encryptKey = "oQSEjMa5KAp4n9bG";
    private static final Integer tokenExpireTime = 24 * 60 * 60 * 1000;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "通过账号密码登录")
    public BaseResponse<String> login(@RequestBody @Validated LoginDTO loginDTO) {
        Optional<MemberEntity> memberEntityOptional = memberService.get(loginDTO.getUsername());
        if (!memberEntityOptional.isPresent()) {
            throw new ParamException("用户不存在");
        }
        try {
            if (!Objects.equals(MD5Util.crypt(loginDTO.getPassword() + encryptKey),
                    memberEntityOptional.get().getPassword())) {
                throw new ParamException("用户名或者密码错误");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ParamException("MD5计算出错");
        }

        return BaseResponse.createSuccessResult(buildJWT(loginDTO.getUsername()));
    }

    private String buildJWT(String username){
        //生成jwt
        Date now = new Date();
        Algorithm algo = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("MING")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("username", username)//保存身份标识
                .sign(algo);
    }
}
