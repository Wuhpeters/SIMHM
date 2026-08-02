package com.SIMHM.provider.ana.auth.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.exception.AnaAuthException;
import com.SIMHM.provider.ana.response.AnaAuthItemsResponse;
import com.SIMHM.provider.ana.response.AnaAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

class AnaAuthProviderTest {

    @Test
    void shouldCacheTokenInMemory() {
        TestProvider provider = new TestProvider(responseWithToken("abc123"));

        String first = provider.getToken();
        String second = provider.getToken();

        assertEquals("abc123", first);
        assertEquals("abc123", second);
        assertEquals(1, provider.calls);
    }

    @Test
    void shouldRefreshTokenAfterExpiry() throws Exception {
        TestProvider provider = new TestProvider(
                responseWithToken("tok1"),
                responseWithToken("tok2"));

        String first = provider.getToken();
        forceTokenExpiry(provider);
        String second = provider.getToken();

        assertEquals("tok1", first);
        assertEquals("tok2", second);
        assertEquals(2, provider.calls);
    }

    @Test
    void shouldExposeExpirationCheck() {
        TestProvider provider = new TestProvider(responseWithToken("abc123"));

        provider.getToken();

        assertFalse(provider.expired());
    }

    @Test
    void shouldThrowWhenTokenIsMissing() {
        TestProvider provider = new TestProvider(new AnaAuthResponse("OK", 200, "Sucesso", new AnaAuthItemsResponse()));

        assertThrows(AnaAuthException.class, provider::getToken);
    }

    private static void forceTokenExpiry(AnaAuthProvider provider) throws Exception {
        Field expiryField = AnaAuthProvider.class.getDeclaredField("expiry");
        expiryField.setAccessible(true);
        expiryField.set(provider, Instant.now().minusSeconds(1));
    }

    private static AnaAuthResponse responseWithToken(String token) {
        AnaAuthItemsResponse items = new AnaAuthItemsResponse();
        items.setTokenautenticacao(token);
        return new AnaAuthResponse("OK", 200, "Sucesso", items);
    }

    private static final class TestProvider extends AnaAuthProvider {
        private final Deque<AnaAuthResponse> responses = new ArrayDeque<>();
        private int calls;

        private TestProvider(AnaAuthResponse... responses) {
            super("https://example.test/auth", HttpClient.newHttpClient(), new ObjectMapper(), "user", "pass");
            for (AnaAuthResponse response : responses) {
                this.responses.addLast(response);
            }
        }

        @Override
        protected AnaAuthResponse fetchToken(String username, String password) {
            calls++;
            return responses.removeFirst();
        }

        private boolean expired() {
            return isTokenExpired();
        }
    }
}
