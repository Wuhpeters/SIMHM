package com.SIMHM.provider.ana.hydrology.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.SIMHM.provider.ana.hydrology.AnaHydrologyStationsProvider;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;
import com.SIMHM.provider.ana.response.AnaHydrologyStation;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;

class AnaHydrologyStationsProviderTest {

//    @Test
//    void shouldBuildUriAndParseResponse() {
//        class TestProvider extends AnaHydrologyStationsProvider {
//            private HttpRequest captured;
//
//            @Override
//            protected HttpResponse<String> send(HttpRequest request) {
//                captured = request;
//                return new SimpleResponse(200,
//                        "{"
//                                + "\"status\":\"OK\","
//                                + "\"code\":200,"
//                                + "\"message\":\"Sucesso\","
//                                + "\"items\":[{"
//                                + "\"Altitude\":\"472.5\","
//                                + "\"Bacia_Nome\":\"ATL\\u00c2NTICO, TRECHO SUDESTE\","
//                                + "\"Data_Ultima_Atualizacao\":\"2016-11-14 00:00:00.0\","
//                                + "\"Estacao_Nome\":\"CRUZ ALTA\","
//                                + "\"Latitude\":\"-28.6364\","
//                                + "\"Longitude\":\"-53.5994\","
//                                + "\"Municipio_Codigo\":\"24060000\","
//                                + "\"Municipio_Nome\":\"CRUZ ALTA\","
//                                + "\"Operadora_Codigo\":\"5\","
//                                + "\"Operadora_Sigla\":\"INMET\","
//                                + "\"Operadora_Sub_Unidade_UF\":null,"
//                                + "\"Operando\":\"1\","
//                                + "\"Responsavel_Codigo\":\"5\","
//                                + "\"Responsavel_Sigla\":\"INMET\","
//                                + "\"Responsavel_Unidade_UF\":null,"
//                                + "\"Rio_Codigo\":null,"
//                                + "\"Rio_Nome\":\"N/A\","
//                                + "\"Sub_Bacia_Codigo\":\"75\","
//                                + "\"Sub_Bacia_Nome\":\"RIOS URUGUAI,IJU\\u00cd E OUTROS\","
//                                + "\"UF_Estacao\":\"RS\","
//                                + "\"UF_Nome_Estacao\":\"RIO GRANDE DO SUL\","
//                                + "\"codigobacia\":\"8\","
//                                + "\"codigoestacao\":\"2853005\""
//                                + "}]}");
//            }
//
//            HttpRequest capturedRequest() {
//                return captured;
//            }
//        }
//
//        TestProvider provider = new TestProvider();
//        AnaHydrologyStationsResponse response = provider.listStations(
//                new AnaHydrologyStationsRequest("RS", "8"));
//
//        assertNotNull(response);
//        assertEquals("OK", response.getStatus());
//        assertEquals(200, response.getCode());
//        assertEquals("Sucesso", response.getMessage());
//        assertEquals(1, response.getItems().size());
//
//        AnaHydrologyStation station = response.getItems().get(0);
//        assertEquals("472.5", station.getAltitude());
//        assertEquals("ATLÂNTICO, TRECHO SUDESTE", station.getBaciaNome());
//        assertEquals("2016-11-14 00:00:00.0", station.getDataUltimaAtualizacao());
//        assertEquals("CRUZ ALTA", station.getEstacaoNome());
//        assertEquals("-28.6364", station.getLatitude());
//        assertEquals("-53.5994", station.getLongitude());
//        assertEquals("24060000", station.getMunicipioCodigo());
//        assertEquals("CRUZ ALTA", station.getMunicipioNome());
//        assertEquals("5", station.getOperadoraCodigo());
//        assertEquals("INMET", station.getOperadoraSigla());
//        assertNull(station.getOperadoraSubUnidadeUf());
//        assertEquals("1", station.getOperando());
//        assertEquals("5", station.getResponsavelCodigo());
//        assertEquals("INMET", station.getResponsavelSigla());
//        assertNull(station.getResponsavelUnidadeUf());
//        assertNull(station.getRioCodigo());
//        assertEquals("N/A", station.getRioNome());
//        assertEquals("75", station.getSubBaciaCodigo());
//        assertEquals("RIOS URUGUAI,IJUÍ E OUTROS", station.getSubBaciaNome());
//        assertEquals("RS", station.getUfEstacao());
//        assertEquals("RIO GRANDE DO SUL", station.getUfNomeEstacao());
//        assertEquals("8", station.getCodigoBacia());
//        assertEquals("2853005", station.getCodigoEstacao());
//        assertTrue(provider.capturedRequest().uri().toString().contains("Unidade%20Federativa=RS"));
//        assertTrue(provider.capturedRequest().uri().toString().contains("C%C3%B3digo%20da%20Bacia=8"));
//    }

//    @Test
//    void shouldIncludeAuthTokenInRequest() {
//        class TestAuthProvider extends AnaAuthProvider {
//            TestAuthProvider() {
//                super("user", "pass");
//            }
//
//            @Override
//            public String getToken() {
//                return "abc-token";
//            }
//        }
//
//        class TestProvider extends AnaHydrologyStationsProvider {
//            private HttpRequest captured;
//
//            TestProvider(AnaAuthProvider authProvider) {
////                super(authProvider);
//            }
//
//            @Override
//            protected HttpResponse<String> send(HttpRequest request) {
//                captured = request;
//                return new SimpleResponse(200, "{\"status\":\"OK\",\"code\":200,\"message\":\"Sucesso\",\"items\":[]}");
//            }
//
//            HttpRequest capturedRequest() {
//                return captured;
//            }
//        }
//
//        TestProvider provider = new TestProvider(new TestAuthProvider());
//        provider.listStations(new AnaHydrologyStationsRequest("RS", "8"));
//
//        assertTrue(provider.capturedRequest().uri().toString().contains("token=abc-token"));
//    }

//    @Test
//    void shouldThrowOnNonSuccessStatus() {
//        AnaHydrologyStationsProvider provider = new AnaHydrologyStationsProvider() {
//            @Override
//            protected HttpResponse<String> send(HttpRequest request) {
//                return new SimpleResponse(500, "{\"error\":\"boom\"}");
//            }
//        };
//
//        RuntimeException ex = assertThrows(RuntimeException.class,
//                () -> provider.listStations(new AnaHydrologyStationsRequest("RS", "8")));
//
//        assertTrue(ex.getMessage().contains("500"));
//    }
//
//    private static final class SimpleResponse implements HttpResponse<String> {
//        private final int statusCode;
//        private final String body;
//
//        private SimpleResponse(int statusCode, String body) {
//            this.statusCode = statusCode;
//            this.body = body;
//        }
//
//        @Override
//        public int statusCode() {
//            return statusCode;
//        }
//
//        @Override
//        public HttpRequest request() {
//            return null;
//        }
//
//        @Override
//        public Optional<HttpResponse<String>> previousResponse() {
//            return Optional.empty();
//        }
//
//        @Override
//        public HttpHeaders headers() {
//            return HttpHeaders.of(Map.of(), (a, b) -> true);
//        }
//
//        @Override
//        public String body() {
//            return body;
//        }
//
//        @Override
//        public Optional<javax.net.ssl.SSLSession> sslSession() {
//            return Optional.empty();
//        }
//
//        @Override
//        public URI uri() {
//            return URI.create("https://example.test");
//        }
//
//        @Override
//        public HttpClient.Version version() {
//            return HttpClient.Version.HTTP_1_1;
//        }
//    }
}
