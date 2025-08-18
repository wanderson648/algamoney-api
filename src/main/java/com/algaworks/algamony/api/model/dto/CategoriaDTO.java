package com.algaworks.algamony.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        @NotBlank(message = "O [nome] é obrigatório")
        @Size(min = 3, max = 20, message = "O [nome] deve ter entre 3 e 20 caracteres")
        String nome
) {
}
