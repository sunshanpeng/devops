package com.sunshanpeng.devops.k8s.event;

@FunctionalInterface
public interface MessageFuction {
    void run(String sessionId, String message);
}
