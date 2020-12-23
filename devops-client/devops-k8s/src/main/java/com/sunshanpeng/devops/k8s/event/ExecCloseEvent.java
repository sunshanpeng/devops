package com.sunshanpeng.devops.k8s.event;

import com.sunshanpeng.devops.k8s.dto.ContainerExecDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ExecCloseEvent extends ApplicationEvent {
    private ContainerExecDTO containerExecDTO;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ExecCloseEvent(Object source, ContainerExecDTO containerExecDTO) {
        super(source);
        this.containerExecDTO = containerExecDTO;
    }
}
