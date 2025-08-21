package com.algaworks.algamony.api.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record LancamentoDTO(
        String descricao,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dataVencimentoDe,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dataVencimentoAte
) {
}
