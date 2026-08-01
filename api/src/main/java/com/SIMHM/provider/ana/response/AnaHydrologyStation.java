package com.SIMHM.provider.ana.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnaHydrologyStation {

    @JsonProperty("Altitude")
    private String altitude;
    @JsonProperty("Bacia_Nome")
    private String baciaNome;
    @JsonProperty("Data_Ultima_Atualizacao")
    private String dataUltimaAtualizacao;
    @JsonProperty("Estacao_Nome")
    private String estacaoNome;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonProperty("Municipio_Codigo")
    private String municipioCodigo;
    @JsonProperty("Municipio_Nome")
    private String municipioNome;
    @JsonProperty("Operadora_Codigo")
    private String operadoraCodigo;
    @JsonProperty("Operadora_Sigla")
    private String operadoraSigla;
    @JsonProperty("Operadora_Sub_Unidade_UF")
    private String operadoraSubUnidadeUf;
    @JsonProperty("Operando")
    private String operando;
    @JsonProperty("Responsavel_Codigo")
    private String responsavelCodigo;
    @JsonProperty("Responsavel_Sigla")
    private String responsavelSigla;
    @JsonProperty("Responsavel_Unidade_UF")
    private String responsavelUnidadeUf;
    @JsonProperty("Rio_Codigo")
    private String rioCodigo;
    @JsonProperty("Rio_Nome")
    private String rioNome;
    @JsonProperty("Sub_Bacia_Codigo")
    private String subBaciaCodigo;
    @JsonProperty("Sub_Bacia_Nome")
    private String subBaciaNome;
    @JsonProperty("UF_Estacao")
    private String ufEstacao;
    @JsonProperty("UF_Nome_Estacao")
    private String ufNomeEstacao;
    @JsonProperty("codigobacia")
    private String codigoBacia;
    @JsonProperty("codigoestacao")
    private String codigoEstacao;
}
