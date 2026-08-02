package com.SIMHM.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.SIMHM.controller.response.AnaStation;
import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;

class AnaStationMapperTest {

    @Test
    void shouldMapHydrologyResponseToControllerResponse() {
        AnaHydrologyStation sourceStation = new AnaHydrologyStation();
        sourceStation.setBaciaNome("Bacia A");
        sourceStation.setEstacaoNome("Estacao X");
        sourceStation.setMunicipioCodigo("123");
        sourceStation.setMunicipioNome("Cidade Y");
        sourceStation.setOperadoraSubUnidadeUf(null);
        sourceStation.setRioCodigo("999");
        sourceStation.setRioNome("Rio Z");
        sourceStation.setSubBaciaCodigo("88");
        sourceStation.setSubBaciaNome("Sub Bacia");
        sourceStation.setUfEstacao("RS");
        sourceStation.setUfNomeEstacao("Rio Grande do Sul");
        sourceStation.setCodigoBacia("8");
        sourceStation.setCodigoEstacao("ABC");

        AnaHydrologyStationsResponse source = new AnaHydrologyStationsResponse();
        source.setStatus("OK");
        source.setCode(200);
        source.setMessage("Sucesso");
        source.setItems(List.of(sourceStation));

        AnaStationMapper mapper = new AnaStationMapper();
        AnaStationsResponse result = mapper.toResponse(source);

        assertEquals("OK", result.getStatus());
        assertEquals(200, result.getCode());
        assertEquals("Sucesso", result.getMessage());
        assertEquals(1, result.getItems().size());

        AnaStation station = result.getItems().get(0);
        assertEquals("Bacia A", station.getBaciaNome());
        assertEquals("Estacao X", station.getEstacaoNome());
        assertEquals("123", station.getMunicipioCodigo());
        assertEquals("Cidade Y", station.getMunicipioNome());
        assertNull(station.getOperadoraSubUnidadeUf());
        assertEquals("999", station.getRioCodigo());
        assertEquals("Rio Z", station.getRioNome());
        assertEquals("88", station.getSubBaciaCodigo());
        assertEquals("Sub Bacia", station.getSubBaciaNome());
        assertEquals("RS", station.getUfEstacao());
        assertEquals("Rio Grande do Sul", station.getUfNomeEstacao());
        assertEquals("8", station.getCodigoBacia());
        assertEquals("ABC", station.getCodigoEstacao());
    }
}
