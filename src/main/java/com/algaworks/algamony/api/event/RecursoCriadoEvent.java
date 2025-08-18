package com.algaworks.algamony.api.event;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RecursoCriadoEvent extends ApplicationEvent {

    private final HttpServletResponse response;
    private final Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }
}
