package com.sunshanpeng.devops.member.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.member.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@Api(tags = "鉴权管理")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${auth.jwt.secret:123456}")
    private String secretKey;
    private static final Integer tokenExpireTime = 24 * 60 * 60 * 1000;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "通过账号密码登录")
    public BaseResponse<String> login(@RequestBody @Validated LoginDTO loginDTO) {

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
