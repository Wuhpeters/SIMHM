package com.SIMHM.mapper;

import com.SIMHM.controller.response.AnaStation;
import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import org.springframework.stereotype.Component;

@Component
public class AnaStationMapper {

    public AnaStationsResponse toResponse(AnaHydrologyStationsResponse response) {

        return AnaStationsResponse.builder()
                .status(response.getStatus())
                .code(response.getCode())
                .message(response.getMessage())
                .items(response.getItems()
                        .stream()
                        .map(this::toStation)
                        .toList())
                .build();
    }

    private AnaStation toStation(AnaHydrologyStation station) {

        return AnaStation.builder()
                .baciaNome(station.getBaciaNome())
                .estacaoNome(station.getEstacaoNome())
                .municipioCodigo(station.getMunicipioCodigo())
                .municipioNome(station.getMunicipioNome())
                .operadoraSubUnidadeUf(station.getOperadoraSubUnidadeUf())
                .rioCodigo(station.getRioCodigo())
                .rioNome(station.getRioNome())
                .subBaciaCodigo(station.getSubBaciaCodigo())
                .subBaciaNome(station.getSubBaciaNome())
                .ufEstacao(station.getUfEstacao())
                .ufNomeEstacao(station.getUfNomeEstacao())
                .codigoBacia(station.getCodigoBacia())
                .codigoEstacao(station.getCodigoEstacao())
                .build();
    }
}