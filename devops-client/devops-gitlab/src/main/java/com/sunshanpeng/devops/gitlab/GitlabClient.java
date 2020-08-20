package com.sunshanpeng.devops.gitlab;

import org.gitlab.api.GitlabAPI;

import java.io.IOException;

public class GitlabClient {

    public GitlabAPI getGitlabClient(String hostUrl, String apiToken) {
       return GitlabAPI.connect(hostUrl,apiToken);
    }

    public GitlabAPI getGitlabClient(String hostUrl, String username, String password) throws IOException {
        return GitlabAPI.connect(hostUrl, GitlabAPI.connect(hostUrl, username, password).getPrivateToken());
    }

}
