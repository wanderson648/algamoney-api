package com.algaworks.algamony.api.repository;

import com.algaworks.algamony.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
