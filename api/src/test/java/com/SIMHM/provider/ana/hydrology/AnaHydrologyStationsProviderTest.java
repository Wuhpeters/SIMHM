package com.SIMHM.provider.ana.hydrology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.exception.AnaHydrologyStationsException;
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.mapper.AnaHydrologyStationsMapper;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;
import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

class AnaHydrologyStationsProviderTest {

    @Test
    void shouldBuildUriAndParseResponse() {
        TestProvider provider = new TestProvider();
        provider.nextResponse = new SimpleResponse(200, stationsJson());

        AnaHydrologyStationsResponse response = provider.listStations(
                new AnaHydrologyStationsRequest("RS", "8"));

        assertNotNull(response);
        assertEquals("OK", response.getStatus());
        assertEquals(200, response.getCode());
        assertEquals("Sucesso", response.getMessage());
        assertEquals(1, response.getItems().size());

        AnaHydrologyStation station = response.getItems().get(0);
        assertEquals("472.5", station.getAltitude());
        assertEquals("ATLÂNTICO, TRECHO SUDESTE", station.getBaciaNome());
        assertEquals("2016-11-14 00:00:00.0", station.getDataUltimaAtualizacao());
        assertEquals("CRUZ ALTA", station.getEstacaoNome());
        assertTrue(provider.capturedRequest.uri().toString().contains("Unidade%20Federativa=RS"));
        assertTrue(provider.capturedRequest.uri().toString().contains("C%C3%B3digo%20da%20Bacia=8"));
    }

    @Test
    void shouldIncludeAuthTokenInRequest() {
        TestProvider provider = new TestProvider();
        provider.nextResponse = new SimpleResponse(200, "{\"status\":\"OK\",\"code\":200,\"message\":\"Sucesso\",\"items\":[]}");

        provider.listStations(new AnaHydrologyStationsRequest("RS", "8"));

        assertEquals("Bearer abc-token", provider.capturedRequest.headers().firstValue("Authorization").orElse(""));
    }

    @Test
    void shouldThrowOnNonSuccessStatus() {
        TestProvider provider = new TestProvider();
        provider.nextResponse = new SimpleResponse(500, "{\"error\":\"boom\"}");

        AnaHydrologyStationsException ex = assertThrows(AnaHydrologyStationsException.class,
                () -> provider.listStations(new AnaHydrologyStationsRequest("RS", "8")));

        assertTrue(ex.getMessage().contains("500"));
    }

    @Test
    void shouldThrowWhenRequestIsNull() {
        TestProvider provider = new TestProvider();

        assertThrows(AnaHydrologyStationsException.class, () -> provider.listStations(null));
    }

    @Test
    void shouldEncodeRequestParameters() {
        TestProvider provider = new TestProvider();
        provider.nextResponse = new SimpleResponse(200, "{\"status\":\"OK\",\"code\":200,\"message\":\"Sucesso\",\"items\":[]}");

        provider.listStations(new AnaHydrologyStationsRequest("Rio Grande do Sul", "8 1"));

        String uri = provider.capturedRequest.uri().toString();
        assertTrue(uri.contains("Unidade%20Federativa=Rio+Grande+do+Sul"));
        assertTrue(uri.contains("C%C3%B3digo%20da%20Bacia=8+1"));
    }

    private static String stationsJson() {
        return "{"
                + "\"status\":\"OK\","
                + "\"code\":200,"
                + "\"message\":\"Sucesso\","
                + "\"items\":[{"
                + "\"Altitude\":\"472.5\","
                + "\"Bacia_Nome\":\"ATL\\u00c2NTICO, TRECHO SUDESTE\","
                + "\"Data_Ultima_Atualizacao\":\"2016-11-14 00:00:00.0\","
                + "\"Estacao_Nome\":\"CRUZ ALTA\","
                + "\"Latitude\":\"-28.6364\","
                + "\"Longitude\":\"-53.5994\","
                + "\"Municipio_Codigo\":\"24060000\","
                + "\"Municipio_Nome\":\"CRUZ ALTA\","
                + "\"Operadora_Codigo\":\"5\","
                + "\"Operadora_Sigla\":\"INMET\","
                + "\"Operadora_Sub_Unidade_UF\":null,"
                + "\"Operando\":\"1\","
                + "\"Responsavel_Codigo\":\"5\","
                + "\"Responsavel_Sigla\":\"INMET\","
                + "\"Responsavel_Unidade_UF\":null,"
                + "\"Rio_Codigo\":null,"
                + "\"Rio_Nome\":\"N/A\","
                + "\"Sub_Bacia_Codigo\":\"75\","
                + "\"Sub_Bacia_Nome\":\"RIOS URUGUAI,IJU\\u00cd E OUTROS\","
                + "\"UF_Estacao\":\"RS\","
                + "\"UF_Nome_Estacao\":\"RIO GRANDE DO SUL\","
                + "\"codigobacia\":\"8\","
                + "\"codigoestacao\":\"2853005\""
                + "}]}";
    }

    private static final class TestProvider extends AnaHydrologyStationsProvider {
        private HttpRequest capturedRequest;
        private HttpResponse<String> nextResponse;

        private TestProvider() {
            super(
                    "https://example.test/stations",
                    HttpClient.newHttpClient(),
                    new AnaHydrologyStationsMapper(new ObjectMapper()),
                    new FixedTokenAuthProvider());
        }

        @Override
        protected HttpResponse<String> send(HttpRequest request) {
            this.capturedRequest = request;
            return nextResponse;
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

    private static final class SimpleResponse implements HttpResponse<String> {
        private final int statusCode;
        private final String body;

        private SimpleResponse(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        @Override
        public int statusCode() {
            return statusCode;
        }

        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<String>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return HttpHeaders.of(Map.of(), (a, b) -> true);
        }

        @Override
        public String body() {
            return body;
        }

        @Override
        public Optional<javax.net.ssl.SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return URI.create("https://example.test");
        }

        @Override
        public HttpClient.Version version() {
            return HttpClient.Version.HTTP_1_1;
        }
    }
}
