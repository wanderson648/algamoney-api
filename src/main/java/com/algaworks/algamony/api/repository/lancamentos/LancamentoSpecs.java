package com.algaworks.algamony.api.repository.lancamentos;

import com.algaworks.algamony.api.model.Lancamento;
import com.algaworks.algamony.api.model.dto.LancamentoDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public final class LancamentoSpecs {
    private LancamentoSpecs() {
    }

    public static Specification<Lancamento> comFiltros(LancamentoDTO lancamentoDTO) {
        return (root, query, criteriaBuilder) -> {
            var preds = new ArrayList<Predicate>();

            if (lancamentoDTO.descricao() != null && !lancamentoDTO.descricao().isBlank()) {
                preds.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")),
                        "%" + lancamentoDTO.descricao().toLowerCase() + "%"));
            }
            return criteriaBuilder.and(preds.toArray(Predicate[]::new));
        };
    }
}
