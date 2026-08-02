package com.SIMHM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.http.HttpClient;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.exception.StationInventoryException;
import com.SIMHM.mapper.AnaStationMapper;
import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.mapper.AnaHydrologyStationsMapper;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;
import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

class StationInvetaryServiceTest {

    @Test
    void shouldMapProviderResponseWhenStationsExist() {
        AnaHydrologyStationsResponse providerResponse = new AnaHydrologyStationsResponse();
        providerResponse.setItems(List.of(new AnaHydrologyStation()));

        AnaStationsResponse expected = new AnaStationsResponse();
        expected.setStatus("OK");

        StubProvider provider = new StubProvider(providerResponse);
        StubMapper mapper = new StubMapper(expected);
        StationInvetaryService service = new StationInvetaryService(provider, mapper);

        AnaStationsResponse result = service.getStations();

        assertSame(expected, result);
        assertSame(providerResponse, mapper.capturedResponse);
        assertEquals("RS", provider.capturedRequest.getState());
        assertEquals("8", provider.capturedRequest.getBasinCode());
    }

    @Test
    void shouldThrowWhenProviderReturnsNoStations() {
        AnaHydrologyStationsResponse providerResponse = new AnaHydrologyStationsResponse();
        providerResponse.setItems(List.of());

        StationInvetaryService service = new StationInvetaryService(
                new StubProvider(providerResponse),
                new StubMapper(new AnaStationsResponse()));

        assertThrows(StationInventoryException.class, service::getStations);
    }

    private static final class StubProvider extends AnaHydrologyStationsProvider {
        private final AnaHydrologyStationsResponse response;
        private AnaHydrologyStationsRequest capturedRequest;

        private StubProvider(AnaHydrologyStationsResponse response) {
            super(
                    "https://example.test/stations",
                    HttpClient.newHttpClient(),
                    new AnaHydrologyStationsMapper(new ObjectMapper()),
                    new FixedTokenAuthProvider());
            this.response = response;
        }

        @Override
        public AnaHydrologyStationsResponse listStations(AnaHydrologyStationsRequest request) {
            this.capturedRequest = request;
            return response;
        }
    }

    private static final class StubMapper extends AnaStationMapper {
        private final AnaStationsResponse mapped;
        private AnaHydrologyStationsResponse capturedResponse;

        private StubMapper(AnaStationsResponse mapped) {
            this.mapped = mapped;
        }

        @Override
        public AnaStationsResponse toResponse(AnaHydrologyStationsResponse response) {
            this.capturedResponse = response;
            return mapped;
        }
    }

    private static final class FixedTokenAuthProvider extends AnaAuthProvider {
        private FixedTokenAuthProvider() {
            super("https://example.test/auth", HttpClient.newHttpClient(), new ObjectMapper(), "user", "pass");
        }

        @Override
        public String getToken() {
            return "abc-token";
        }
    }
}
