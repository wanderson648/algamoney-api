package com.algaworks.algamony.api.exception.dto;

public record ErrorDTO(
        String mensagemUsuario,
        String mensagemDesenvolvedor
) {
}
