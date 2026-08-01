package com.SIMHM.provider.ana.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnaAuthItemsResponse {

    private Boolean sucesso;
    private String token;
    private String validade;
    private String retorno;
    private String httpStatus;
    private String link;
    private Boolean tokenValido;
    private String tokenautenticacao;
    private String respostaautenticacao;
}
