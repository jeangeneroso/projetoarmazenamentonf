package com.equipeturma862.cadastronf.service;

import com.equipeturma862.cadastronf.domain.Requisicao;
import com.equipeturma862.cadastronf.repository.RequisicaoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RequisicaoServiceImpl implements RequisicaoService {
	
   private final RequisicaoRepository requisicaoRepository;

	    @Override
	    public List<Requisicao> list (String nome) {
	        return IterableUtils.toList(requisicaoRepository.findAll());
	    }

	    @Override
	    public Requisicao save(Requisicao requisicao) {
	        return requisicaoRepository.save(requisicao);
	    }

	    @Override
	    public Requisicao getById(Long id) {
	        return requisicaoRepository.findById(id).get();
	    }

	    @Override
	    public Requisicao update(Long id, Requisicao requisicao) {
	        requisicao.setId(id);
	        return requisicaoRepository.save(requisicao);
	    }

	    @Override
	    public void delete(Long id) {
	    	requisicaoRepository.deleteById(id);
	    }
	   
}
