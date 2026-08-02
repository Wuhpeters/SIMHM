package com.SIMHM.service;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.exception.StationInventoryException;
import com.SIMHM.mapper.AnaStationMapper;
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StationInvetaryService {

    private final AnaStationMapper anaStationMapper;
    private final AnaHydrologyStationsProvider anaHydrologyStationsProvider;
    private static final Logger log = LoggerFactory.getLogger(StationInvetaryService.class);

    public AnaStationsResponse getStations() {
        log.info(ApplicationMessages.STATION_SERVICE_REQUEST);
        AnaHydrologyStationsResponse response = anaHydrologyStationsProvider
                .listStations(new AnaHydrologyStationsRequest("RS", "8"));

        if (response.getItems().isEmpty()) {
            log.warn(ApplicationMessages.STATION_SERVICE_EMPTY);

            throw new StationInventoryException(
                    ApplicationMessages.EXCEPTION_STATION_NOT_FOUND);
        }
        
        log.info(ApplicationMessages.STATION_SERVICE_SUCCESS);
        return anaStationMapper.toResponse(response);
    }
}
