package com.SIMHM.provider.ana.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;

class AnaHydrologyStationsMapperTest {

    @Test
    void shouldMapHydrologyStationsResponse() {
        String json = "{"
                + "\"status\":\"OK\","
                + "\"code\":200,"
                + "\"message\":\"Sucesso\","
                + "\"items\":[{"
                + "\"Altitude\":\"472.5\","
                + "\"Bacia_Nome\":\"ATL\\u00c2NTICO, TRECHO SUDESTE\","
                + "\"Data_Ultima_Atualizacao\":\"2016-11-14 00:00:00.0\","
                + "\"Estacao_Nome\":\"CRUZ ALTA\","
                + "\"Latitude\":\"-28.6364\","
                + "\"Longitude\":\"-53.5994\","
                + "\"Municipio_Codigo\":\"24060000\","
                + "\"Municipio_Nome\":\"CRUZ ALTA\","
                + "\"Operadora_Codigo\":\"5\","
                + "\"Operadora_Sigla\":\"INMET\","
                + "\"Operadora_Sub_Unidade_UF\":null,"
                + "\"Operando\":\"1\","
                + "\"Responsavel_Codigo\":\"5\","
                + "\"Responsavel_Sigla\":\"INMET\","
                + "\"Responsavel_Unidade_UF\":null,"
                + "\"Rio_Codigo\":null,"
                + "\"Rio_Nome\":\"N/A\","
                + "\"Sub_Bacia_Codigo\":\"75\","
                + "\"Sub_Bacia_Nome\":\"RIOS URUGUAI,IJU\\u00cd E OUTROS\","
                + "\"UF_Estacao\":\"RS\","
                + "\"UF_Nome_Estacao\":\"RIO GRANDE DO SUL\","
                + "\"codigobacia\":\"8\","
                + "\"codigoestacao\":\"2853005\""
                + "}]}";

        AnaHydrologyStationsMapper mapper = new AnaHydrologyStationsMapper();
        AnaHydrologyStationsResponse response = mapper.toResponse(json);

        assertEquals("OK", response.getStatus());
        assertEquals(200, response.getCode());
        assertEquals("Sucesso", response.getMessage());
        assertEquals(1, response.getItems().size());

        AnaHydrologyStation station = response.getItems().get(0);
        assertEquals("472.5", station.getAltitude());
        assertEquals("ATLÂNTICO, TRECHO SUDESTE", station.getBaciaNome());
        assertEquals("2016-11-14 00:00:00.0", station.getDataUltimaAtualizacao());
        assertEquals("CRUZ ALTA", station.getEstacaoNome());
        assertEquals("-28.6364", station.getLatitude());
        assertEquals("-53.5994", station.getLongitude());
        assertEquals("24060000", station.getMunicipioCodigo());
        assertEquals("CRUZ ALTA", station.getMunicipioNome());
        assertEquals("5", station.getOperadoraCodigo());
        assertEquals("INMET", station.getOperadoraSigla());
        assertNull(station.getOperadoraSubUnidadeUf());
        assertEquals("1", station.getOperando());
        assertEquals("5", station.getResponsavelCodigo());
        assertEquals("INMET", station.getResponsavelSigla());
        assertNull(station.getResponsavelUnidadeUf());
        assertNull(station.getRioCodigo());
        assertEquals("N/A", station.getRioNome());
        assertEquals("75", station.getSubBaciaCodigo());
        assertEquals("RIOS URUGUAI,IJUÍ E OUTROS", station.getSubBaciaNome());
        assertEquals("RS", station.getUfEstacao());
        assertEquals("RIO GRANDE DO SUL", station.getUfNomeEstacao());
        assertEquals("8", station.getCodigoBacia());
        assertEquals("2853005", station.getCodigoEstacao());
    }
}
