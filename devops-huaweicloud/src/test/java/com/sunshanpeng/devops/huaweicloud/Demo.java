package com.sunshanpeng.devops.huaweicloud;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        //设置认证参数
        String authUrl = "https://iam.cn-north-1.myhuaweicloud.com/v3";//endpointUrl
        String user = "replace-with-your-username";//用户名
        String password = "replace-with-your-password";//用户密码
        String projectId = "replace-with-your-projectId";//项目ID
        String userDomainId = "replace-with-your-domainId";//账号ID

        //初始化client
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //设置查询参数
        Map<String, String> filter = new HashMap<String, String>();

        //将需要输入的参数都放入filter里面
        filter.put("limit", "3");

        //调用查询虚拟机列表的接口
        List<? extends Server> serverList = os.compute().servers().list(filter);
        if (serverList.size() > 0) {
            System.out.println("get serverList success, size = " + serverList.size());
            for (Server server : serverList) {
                System.out.println(server);
            }
        } else {
            System.out.println("no server exists.");
        }
    }

}
