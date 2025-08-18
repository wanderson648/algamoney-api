package com.algaworks.algamony.api.model.dto;


import com.algaworks.algamony.api.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDTO(
        @NotBlank(message = "Campo [nome] é obrigatório")
        String nome,
        @NotNull(message = "Campo [ativo] é obrigatório")
        Boolean ativo,
        Endereco endereco
) {
}