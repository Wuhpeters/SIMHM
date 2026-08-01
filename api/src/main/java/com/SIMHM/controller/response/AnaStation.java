package com.SIMHM.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnaStation {

    private String baciaNome;
    private String estacaoNome;
    private String municipioCodigo;
    private String municipioNome;
    private String operadoraSubUnidadeUf;
    private String rioCodigo;
    private String rioNome;
    private String subBaciaCodigo;
    private String subBaciaNome;
    private String ufEstacao;
    private String ufNomeEstacao;
    private String codigoBacia;
    private String codigoEstacao;
}
