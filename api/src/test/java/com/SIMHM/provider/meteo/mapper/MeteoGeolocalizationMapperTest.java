package com.SIMHM.provider.meteo.mapper;

import com.SIMHM.controller.response.GeolocalizationResponse;
import com.SIMHM.provider.meteo.response.GeolocalizationLocation;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeteoGeolocalizationMapperTest {

    private final MeteoGeolocalizationMapper mapper = new MeteoGeolocalizationMapper();

    @Test
    void shouldReturnEmptyWhenResponseIsNull() {
        GeolocalizationResponse resp = mapper.toResponse(null);

        assertNotNull(resp);
        assertNull(resp.getCity());
        assertNull(resp.getState());
        assertNull(resp.getCountry());
        assertNull(resp.getLatitude());
        assertNull(resp.getLongitude());
    }

    @Test
    void shouldReturnEmptyWhenResultsEmpty() {
        MeteoGeolocalizationResponse meta = new MeteoGeolocalizationResponse();
        meta.setResults(java.util.List.of());

        GeolocalizationResponse resp = mapper.toResponse(meta);

        assertNotNull(resp);
        assertNull(resp.getCity());
    }

    @Test
    void shouldMapFirstLocationToResponse() {
        var loc = new GeolocalizationLocation();
        loc.setName("Recife");
        loc.setAdmin1("Pernambuco");
        loc.setCountry("BR");
        loc.setLatitude(-8.0);
        loc.setLongitude(-34.9);

        var meta = new MeteoGeolocalizationResponse();
        meta.setResults(java.util.List.of(loc));

        GeolocalizationResponse resp = mapper.toResponse(meta);

        assertNotNull(resp);
        assertEquals("Recife", resp.getCity());
        assertEquals("Pernambuco", resp.getState());
        assertEquals("BR", resp.getCountry());
        assertEquals("-8.0", resp.getLatitude());
        assertEquals("-34.9", resp.getLongitude());
    }
}
