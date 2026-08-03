package com.SIMHM.controller;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.net.http.HttpClient;

import org.junit.jupiter.api.Test;

import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.mapper.AnaStationMapper;
import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.mapper.AnaHydrologyStationsMapper;
import com.SIMHM.service.StationInvetaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

class AnaControllerTest {

    @Test
    void shouldDelegateToService() {
        AnaStationsResponse expected = new AnaStationsResponse();
        expected.setStatus("OK");

        AnaController controller = new AnaController(new StubService(expected));

        AnaStationsResponse result = controller.stations();

        assertSame(expected, result);
    }

    private static final class StubService extends StationInvetaryService {
        private final AnaStationsResponse response;

        private StubService(AnaStationsResponse response) {
            super(
                    new AnaStationMapper(),
                    new StubProvider());
            this.response = response;
        }

        @Override
        public AnaStationsResponse getStations() {
            return response;
        }
    }

    private static final class StubProvider extends AnaHydrologyStationsProvider {
        private StubProvider() {
            super(
                    "https://example.test/stations",
                    HttpClient.newHttpClient(),
                    new AnaHydrologyStationsMapper(new ObjectMapper()),
                    new FixedTokenAuthProvider());
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
