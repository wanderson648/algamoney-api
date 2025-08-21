package com.algaworks.algamony.api.service;

import com.algaworks.algamony.api.event.RecursoCriadoEvent;
import com.algaworks.algamony.api.exception.RecursoNaoEncontrado;
import com.algaworks.algamony.api.model.Lancamento;
import com.algaworks.algamony.api.model.dto.LancamentoDTO;
import com.algaworks.algamony.api.repository.LancamentoRepository;
import com.algaworks.algamony.api.repository.lancamentos.LancamentoSpecs;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Lancamento salvar(Lancamento lancamento, HttpServletResponse response) {
        var lancamentoNovo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoNovo.getId()));
        return lancamentoNovo;
    }


    @Transactional(readOnly = true)
    public List<Lancamento> listar(LancamentoDTO filtro) {
        return lancamentoRepository.findAll(LancamentoSpecs.comFiltros(filtro));
    }

    @Transactional
    public Lancamento buscarPorId(Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Recurso não encontrado"));
    }
}
