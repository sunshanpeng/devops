package com.sunshanpeng.devops.huaweicloud;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;


/**
 * 华为云客户端
 * @see <a href="https://support.huaweicloud.com/devg-sdk/sdk_01_0005.html"/>
 */
public class HuaweicloudClient {

    private OSClient osclient;

    public HuaweicloudClient(OSClient osclient) {
        this.osclient = osclient;
    }

    /**
     * AK/SK认证
     * @param accessKeyId AK
     * @param accessKeySecret SK
     * @param regionId "cn-east-3"
     * @param projectId
     */
    public HuaweicloudClient(String accessKeyId, String accessKeySecret, String regionId, String projectId) {
        osclient = OSFactory.builderAKSK()
                .credentials(accessKeyId, accessKeySecret, regionId, projectId, "myhuaweicloud.com")
                .authenticate();
    }

    /**
     * 账号密码token认证
     * @param authUrl https://iam.cn-north-1.myhuaweicloud.com/v3
     * @param username 登录名
     * @param password 密码
     * @param projectId 项目ID
     * @param userDomainId 账号ID
     * @see <a href="https://support.huaweicloud.com/devg-sdk/sdk_05_0003.html"/>
     */
    public HuaweicloudClient(String authUrl, String username, String password, String projectId, String userDomainId) {
        osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(username, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();
    }

    public OSClient getClient() {
        return osclient;
    }
}
