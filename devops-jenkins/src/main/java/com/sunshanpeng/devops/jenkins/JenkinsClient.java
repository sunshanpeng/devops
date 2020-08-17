package com.sunshanpeng.devops.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;

import java.net.URI;
import java.net.URISyntaxException;

public class JenkinsClient {

    public JenkinsHttpClient getJenkinsHttpClient(String uri, String username, String password) throws URISyntaxException {
        return new JenkinsHttpClient(new URI(uri), username, password);
    }

    public JenkinsServer getJenkinsServer(String uri, String username, String password) throws URISyntaxException {
        return new JenkinsServer(new URI(uri), username, password);
    }

}
