package com.sunshanpeng.devops.k8s.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ExecMessageEvent extends ApplicationEvent {
    private String sessionId;

    private String message;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ExecMessageEvent(Object source, String sessionId, String message) {
        super(source);
        this.sessionId = sessionId;
        this.message = message;
    }
}

