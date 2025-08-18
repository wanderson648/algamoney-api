package com.algaworks.algamony.api.resource;

import com.algaworks.algamony.api.event.RecursoCriadoEvent;
import com.algaworks.algamony.api.exception.RecursoNaoEncontrado;
import com.algaworks.algamony.api.model.Pessoa;
import com.algaworks.algamony.api.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pessoas")
public class PessoaResource {


    private final PessoaRepository pessoaRepository;
    private final ApplicationEventPublisher publisher;

    public PessoaResource(PessoaRepository pessoaRepository, ApplicationEventPublisher publisher) {
        this.pessoaRepository = pessoaRepository;
        this.publisher = publisher;
    }

    @PostMapping("/cria")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }


    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pessoaRepository.findById(id).ifPresentOrElse(
                pessoaRepository::delete,
                () -> {
                    throw new RecursoNaoEncontrado("Recurso não encontrado");
                });

        return ResponseEntity.noContent().build();
    }
}
