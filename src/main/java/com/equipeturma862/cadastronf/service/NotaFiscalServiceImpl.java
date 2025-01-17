package com.equipeturma862.cadastronf.service;

import com.equipeturma862.cadastronf.domain.NotaFiscal;
import com.equipeturma862.cadastronf.domain.Remetente;
import com.equipeturma862.cadastronf.exceptions.NotaFiscalExists;
import com.equipeturma862.cadastronf.exceptions.RemetenteNotFound;
import com.equipeturma862.cadastronf.repository.NotasFiscaisRepositoy;
import com.equipeturma862.cadastronf.repository.RemetenteRepositoy;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService{


    private final NotasFiscaisRepositoy notasFiscaisRepositoy;
    private final RemetenteRepositoy remetenteRepositoy;

    @Override
    public List<NotaFiscal> list(String nome) {
        return IterableUtils.toList(notasFiscaisRepositoy.findAll());
    }

    @Override
    public NotaFiscal save(NotaFiscal notaFiscal, Long remetenteId) {
        if (remetenteRepositoy.existsById(remetenteId)) {
            if (notasFiscaisRepositoy.existsByNumeroNotaFiscal(notaFiscal.getNumeroNotaFiscal())) {
                if (notasFiscaisRepositoy.existsByDataEmissao(notaFiscal.getDataEmissao())) {
                    throw new NotaFiscalExists();
                }else {
                    Remetente remetenteBuilder = Remetente.builder()
                            .id(remetenteId).build();
                    notaFiscal.setRemetente(remetenteBuilder);
                    return notasFiscaisRepositoy.save(notaFiscal);
                }
            }else {
                    Remetente remetenteBuilder = Remetente.builder()
                            .id(remetenteId).build();
                    notaFiscal.setRemetente(remetenteBuilder);
                    return notasFiscaisRepositoy.save(notaFiscal);
                }
        }throw new RemetenteNotFound();
    }

    @Override
    public NotaFiscal getById(Long id) {
        return notasFiscaisRepositoy.findById(id).get();
    }

    @Override
    public NotaFiscal update(Long id, NotaFiscal notaFiscal) {
        notaFiscal.setId(id);
        return notasFiscaisRepositoy.save(notaFiscal);
    }

    @Override
    public void delete(Long id) {
        notasFiscaisRepositoy.deleteById(id);

    }
}
