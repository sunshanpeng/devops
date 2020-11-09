package com.sunshanpeng.devops.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;

public class LdapClient {
    /**
     * AD认证
     *
     * @param username 用户名
     * @param password 密码
     */
    public boolean adAuth(String username, String password) {
        EqualsFilter filter = new EqualsFilter("sAMAccountName", username);
        return ldapTemplate.authenticate("", filter.toString(), password);
    }

    /**
     * LDAP认证
     *
     * @param username 用户名
     * @param password 密码
     *  attribute可能是其他名字不是uid
     */
    public boolean ldapAuth(String username, String password) {
        EqualsFilter filter = new EqualsFilter("uid", username);
        return ldapTemplate.authenticate("", filter.toString(), password);
    }

    @Autowired
    private LdapTemplate ldapTemplate;
}
