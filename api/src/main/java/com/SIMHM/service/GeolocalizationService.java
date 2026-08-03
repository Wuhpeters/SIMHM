package com.SIMHM.service;

import com.SIMHM.controller.request.GeolocalizationRequest;
import com.SIMHM.controller.response.GeolocalizationResponse;
import com.SIMHM.provider.meteo.geolocalization.MeteoGeolocalizationProvider;
import com.SIMHM.provider.meteo.mapper.MeteoGeolocalizationMapper;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.provider.meteo.exception.MeteoGeolocalizationException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GeolocalizationService {

    private final MeteoGeolocalizationMapper meteoGeolocalizationMapper;
    private final MeteoGeolocalizationProvider meteoGeolocalizationProvider;
    private static final Logger log = LoggerFactory.getLogger(GeolocalizationService.class);

    public GeolocalizationResponse getLocalization(GeolocalizationRequest localization) {

        log.info(ApplicationMessages.METEO_GEOLOC_REQUEST);
        MeteoGeolocalizationResponse response = meteoGeolocalizationProvider.coordinates(localization);

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            log.warn(ApplicationMessages.METEO_GEOLOC_RESPONSE_ERROR);
            throw new MeteoGeolocalizationException(ApplicationMessages.EXCEPTION_METEO_GEOLOC);
        }

        log.info(ApplicationMessages.METEO_GEOLOC_SUCCESS);
        return meteoGeolocalizationMapper.toResponse(response);
    }
}
