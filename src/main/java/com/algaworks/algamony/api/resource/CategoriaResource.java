package com.algaworks.algamony.api.resource;

import com.algaworks.algamony.api.model.Categoria;
import com.algaworks.algamony.api.service.CategoriaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @PostMapping("/cria")
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaService.salvar(categoria, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}/busca")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(categoriaService.buscarPeloCodigo(codigo));
    }
}
