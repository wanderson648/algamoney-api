package com.algaworks.algamony.api.mapper;

import com.algaworks.algamony.api.model.Categoria;
import com.algaworks.algamony.api.model.Lancamento;
import com.algaworks.algamony.api.model.Pessoa;
import com.algaworks.algamony.api.model.dto.LancamentoRequestDTO;

public class LancamentoMapper {

    public static Lancamento fromDtoToLancamento(LancamentoRequestDTO dto, Pessoa pessoa, Categoria categoria) {
        return Lancamento.builder()
                .descricao(dto.descricao())
                .dataVencimento(dto.dataVencimento())
                .dataPagamento(dto.dataPagamento())
                .valor(dto.valor())
                .observacao(dto.observacao())
                .tipo(dto.tipo())
                .pessoa(pessoa)
                .categoria(categoria)
                .build();
    }
}
