package com.algaworks.algamony.api.repository;

import com.algaworks.algamony.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
