package com.algaworks.algamony.api.service;

import com.algaworks.algamony.api.event.RecursoCriadoEvent;
import com.algaworks.algamony.api.exception.RecursoNaoEncontrado;
import com.algaworks.algamony.api.model.Lancamento;
import com.algaworks.algamony.api.model.Pessoa;
import com.algaworks.algamony.api.model.dto.LancamentoDTO;
import com.algaworks.algamony.api.repository.CategoriaRepository;
import com.algaworks.algamony.api.repository.LancamentoRepository;
import com.algaworks.algamony.api.repository.PessoaRepository;
import com.algaworks.algamony.api.repository.lancamentos.LancamentoSpecs;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PessoaRepository pessoaRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Lancamento salvar(Lancamento lancamento, HttpServletResponse response) {
        boolean pessoa = pessoaRepository.existsById(lancamento.getPessoa().getId());
        boolean lancamentoBanco = categoriaRepository.existsById(lancamento.getCategoria().getCodigo());

        if(!pessoa || !lancamentoBanco) {
            throw new RecursoNaoEncontrado("Pessoa/Lancamento não encontrado(a)");
        }

        var lancamentoNovo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoNovo.getId()));
        return lancamentoNovo;
    }


    @Transactional(readOnly = true)
    public Page<Lancamento> listar(LancamentoDTO filtro, Pageable pageable) {

        if(filtro.dataVencimentoDe() != null && filtro.dataVencimentoAte() != null
                && filtro.dataVencimentoDe().isAfter(filtro.dataVencimentoAte())) {

            throw new IllegalArgumentException("dataVencimentoDe não pode ser maio que dataVencimentoAte");
        }

        return lancamentoRepository.findAll(LancamentoSpecs.comFiltros(filtro), pageable);
    }

    @Transactional
    public Lancamento buscarPorId(Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Recurso não encontrado"));
    }
}
