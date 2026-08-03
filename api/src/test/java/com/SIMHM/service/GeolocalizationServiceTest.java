package com.SIMHM.service;

import com.SIMHM.controller.request.GeolocalizationRequest;
import com.SIMHM.controller.response.GeolocalizationResponse;
import com.SIMHM.provider.meteo.geolocalization.MeteoGeolocalizationProvider;
import com.SIMHM.provider.meteo.mapper.MeteoGeolocalizationMapper;
import com.SIMHM.provider.meteo.response.GeolocalizationLocation;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import com.SIMHM.provider.meteo.exception.MeteoGeolocalizationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeolocalizationServiceTest {

    @Mock
    private MeteoGeolocalizationMapper mapper;

    @Mock
    private MeteoGeolocalizationProvider provider;

    @InjectMocks
    private GeolocalizationService service;

    @Test
    void shouldReturnResponseWhenProviderReturnsValid() {
        var request = new GeolocalizationRequest("Recife");

        var location = new GeolocalizationLocation();
        location.setName("Recife");
        location.setAdmin1("Pernambuco");
        location.setCountry("BR");
        location.setLatitude(-8.0);
        location.setLongitude(-34.9);

        var metaResp = new MeteoGeolocalizationResponse();
        metaResp.setResults(List.of(location));

        var expected = GeolocalizationResponse.builder()
                .city("Recife")
                .state("Pernambuco")
                .country("BR")
                .latitude("-8.0")
                .longitude("-34.9")
                .build();

        when(provider.coordinates(request)).thenReturn(metaResp);
        when(mapper.toResponse(metaResp)).thenReturn(expected);

        var resp = service.getLocalization(request);

        assertNotNull(resp);
        assertEquals("Recife", resp.getCity());
        assertEquals("Pernambuco", resp.getState());
        assertEquals("BR", resp.getCountry());
        assertEquals("-8.0", resp.getLatitude());
        assertEquals("-34.9", resp.getLongitude());

        verify(provider).coordinates(request);
        verify(mapper).toResponse(metaResp);
    }

    @Test
    void shouldThrowWhenProviderReturnsNull() {
        when(provider.coordinates(any())).thenReturn(null);

        assertThrows(MeteoGeolocalizationException.class, () -> service.getLocalization(new GeolocalizationRequest("X")));

        verify(provider).coordinates(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    void shouldThrowWhenProviderReturnsEmptyResults() {
        var metaResp = new MeteoGeolocalizationResponse();
        metaResp.setResults(List.of());

        when(provider.coordinates(any())).thenReturn(metaResp);

        assertThrows(MeteoGeolocalizationException.class, () -> service.getLocalization(new GeolocalizationRequest("X")));

        verify(provider).coordinates(any());
        verify(mapper, never()).toResponse(any());
    }
}
