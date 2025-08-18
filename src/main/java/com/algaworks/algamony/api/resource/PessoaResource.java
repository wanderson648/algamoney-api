package com.algaworks.algamony.api.resource;

import com.algaworks.algamony.api.model.Pessoa;
import com.algaworks.algamony.api.model.dto.PessoaDTO;
import com.algaworks.algamony.api.service.PessoaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pessoas")
public class PessoaResource {


    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/cria")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaService.salvar(pessoa, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }


    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pessoaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/atualiza")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaDTO dto) {
        Pessoa pessoaSalva = pessoaService.atulizar(id, dto);
        return ResponseEntity.ok(pessoaSalva);
    }
}
