package com.algaworks.algamony.api.model;

import com.algaworks.algamony.api.model.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public void atualizar(PessoaDTO dto) {
        this.nome = dto.nome();
        this.ativo = dto.ativo();
        this.endereco = dto.endereco();
    }

    public void atualizarParcial(Boolean ativo) {
        this.ativo = ativo;
    }
}
