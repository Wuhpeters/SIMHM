package com.SIMHM.service;

import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.exception.StationInventoryException;
import com.SIMHM.mapper.AnaStationMapper;
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StationInvetaryService {

    private final AnaHydrologyStationsProvider anaHydrologyStationsProvider;
    private final AnaStationMapper anaStationMapper;

    public AnaStationsResponse getStations() {

        AnaHydrologyStationsResponse response =
                anaHydrologyStationsProvider.listStations(
                        new AnaHydrologyStationsRequest("RS", "8"));

        if (response.getItems().isEmpty()) {
            throw new StationInventoryException(
                    "Nenhuma estação foi encontrada para os parâmetros informados.");
        }

        return anaStationMapper.toResponse(response);
    }
}
