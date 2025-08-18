package com.algaworks.algamony.api.resource;

import com.algaworks.algamony.api.event.RecursoCriadoEvent;
import com.algaworks.algamony.api.exception.RecursoNaoEncontrado;
import com.algaworks.algamony.api.model.Categoria;
import com.algaworks.algamony.api.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categorias")
public class CategoriaResource {

    private final CategoriaRepository categoriaRepository;
    private final ApplicationEventPublisher publisher;

    public CategoriaResource(CategoriaRepository categoriaRepository, ApplicationEventPublisher publisher) {
        this.categoriaRepository = categoriaRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping("/cria")
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}/busca")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        return categoriaRepository.findById(codigo)
                .map(c -> ResponseEntity.ok(new Categoria(c.getCodigo(), c.getNome())))
                .orElseThrow(() -> new RecursoNaoEncontrado("Recurso não encontrado"));
    }
}
