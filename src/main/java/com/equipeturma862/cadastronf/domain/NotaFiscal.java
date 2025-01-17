package com.equipeturma862.cadastronf.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NotaFiscal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long numeroNotaFiscal;
    private LocalDate dataEmissao;
    private LocalDate dataPrestacaoServico;
    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TipoNaturezaOperacao tipoNaturezaOperacao;

    @ManyToOne
    private Remetente remetente;

}
