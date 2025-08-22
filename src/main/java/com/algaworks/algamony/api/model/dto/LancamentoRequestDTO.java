package com.algaworks.algamony.api.model.dto;

import com.algaworks.algamony.api.model.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoRequestDTO(

        @NotBlank(message = "Campo [descricao] é obrigatório")
        String descricao,

        @NotNull(message = "Campo [dataVencimento] é obrigatório")
        LocalDate dataVencimento,

        LocalDate dataPagamento,

        @NotNull(message = "Campo [valor] é obrigatório")
        BigDecimal valor,

        String observacao,

        @NotNull(message = "Campo [tipo] é obrigatório")
        TipoLancamento tipo,

        @NotNull(message = "Campo [categoriaId] é obrigatório")
        Long categoriaId,

        @NotNull(message = "Campo [pessoaId] é obrigatório")
        Long pessoaId
) {
}
