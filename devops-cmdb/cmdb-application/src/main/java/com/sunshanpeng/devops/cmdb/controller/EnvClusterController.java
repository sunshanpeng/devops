package com.sunshanpeng.devops.cmdb.controller;

import com.sunshanpeng.devops.cmdb.domain.entity.ClusterEntity;
import com.sunshanpeng.devops.cmdb.service.EnvClusterService;
import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.log.MethodLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "环境集群管理")
@RestController
public class EnvClusterController {

    @Resource
    private EnvClusterService envClusterService;

    @PostMapping("/clusters")
    @MethodLogger
    @ApiOperation(value = "添加集群")
    public BaseResponse<ClusterEntity> addCluster(@RequestBody @Validated ClusterEntity body) {
        return BaseResponse.createSuccessResult(envClusterService.save(body));
    }

    @GetMapping("/clusters")
    @ApiOperation(value = "获取所有集群")
    public BaseResponse<List<ClusterEntity>> getClusters() {
        return BaseResponse.createSuccessResult(envClusterService.findAllClusters());
    }
}
