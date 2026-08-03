package com.SIMHM.provider.meteo.mapper;

import com.SIMHM.controller.response.GeolocalizationResponse;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeteoGeolocalizationMapper {
    public GeolocalizationResponse toResponse(MeteoGeolocalizationResponse response) {

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            return GeolocalizationResponse.builder().build();
        }

        var loc = response.getResults().get(0);

        return GeolocalizationResponse.builder()
                .city(loc.getName())
                .state(loc.getAdmin1())
                .country(loc.getCountry())
                .latitude(loc.getLatitude() != null ? loc.getLatitude().toString() : null)
                .longitude(loc.getLongitude() != null ? loc.getLongitude().toString() : null)
                .build();
    }
}
