package com.algaworks.algamony.api.service;

import com.algaworks.algamony.api.event.RecursoCriadoEvent;
import com.algaworks.algamony.api.exception.RecursoNaoEncontrado;
import com.algaworks.algamony.api.model.Pessoa;
import com.algaworks.algamony.api.model.dto.PessoaDTO;
import com.algaworks.algamony.api.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ApplicationEventPublisher publisher;

    public PessoaService(PessoaRepository pessoaRepository, ApplicationEventPublisher publisher) {
        this.pessoaRepository = pessoaRepository;
        this.publisher = publisher;
    }


    @Transactional(readOnly = true)
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public Pessoa salvar(@Valid Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getId()));
        return pessoaSalva;
    }

    @Transactional
    public Pessoa atulizar(Long id, @Valid PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Recurso não encontrado"));

        pessoa.atualizar(dto);
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void remover(Long id) {
        pessoaRepository.findById(id).ifPresentOrElse(
                pessoaRepository::delete,
                () -> {
                    throw new RecursoNaoEncontrado("Recurso não encontrado");
                });
    }

    @Transactional
    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Recurso não encontrado"));

        pessoa.atualizarParcial(ativo);
        pessoaRepository.save(pessoa);
    }
}
