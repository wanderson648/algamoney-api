package com.algaworks.algamony.api.repository;

import com.algaworks.algamony.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
