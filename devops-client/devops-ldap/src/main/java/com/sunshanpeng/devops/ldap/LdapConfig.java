package com.sunshanpeng.devops.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * 配置LDAP数据源
 */
@Configuration
public class LdapConfig {

    @Value("${ldap.admin.pwd}")
    private String ldapPwd;

    @Bean
    public LdapContextSource ldapContextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setBase("dc=sunshanpeng,dc=com");
        ldapContextSource.setUrl("ldap://ldap.sunshanpeng.com:389");
        ldapContextSource.setUserDn("cn=admin,dc=sunshanpeng,dc=com");
        ldapContextSource.setPassword(ldapPwd);
        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
    }

    @Bean
    public LdapClient ldapClient() {
        return new LdapClient();
    }

}
