package com.algaworks.algamony.api.resource;

import com.algaworks.algamony.api.model.Lancamento;
import com.algaworks.algamony.api.model.dto.LancamentoDTO;
import com.algaworks.algamony.api.service.LancamentoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/lancamentos")
@RequiredArgsConstructor
public class LancamentoResource {

    private final LancamentoService lancamentoService;

    @PostMapping("/cria")
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoNovo = lancamentoService.salvar(lancamento, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoNovo);
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> listar(@ModelAttribute LancamentoDTO filtro) {
        return ResponseEntity.ok(lancamentoService.listar(filtro));
    }

    @GetMapping("/{id}/busca")
    public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lancamentoService.buscarPorId(id));
    }

}
