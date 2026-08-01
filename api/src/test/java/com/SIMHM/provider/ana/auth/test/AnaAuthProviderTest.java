package com.SIMHM.provider.ana.auth.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.response.AnaAuthResponse;

class AnaAuthProviderTest {

//    @Test
//    void shouldCacheTokenInMemory() {
//        AnaAuthProvider provider = new AnaAuthProvider("user", "pass") {
//            @Override
//            protected AnaAuthResponse fetchToken(String username, String password) {
//                AnaAuthResponse response = new AnaAuthResponse();
//                response.getItems().setTokenautenticacao("abc123");
//                return response;
//            }
//        };
//
//        String first = provider.getToken();
//
//        assertNotNull(first);
//        assertEquals("abc123", first);
//    }
//
//    @Test
//    void shouldRefreshTokenAfterExpiry() throws InterruptedException {
//        AnaAuthProvider provider = new AnaAuthProvider("user", "pass") {
//            private int calls;
//
//            @Override
//            protected AnaAuthResponse fetchToken(String username, String password) {
//                calls++;
//                AnaAuthResponse response = new AnaAuthResponse();
//                response.getItems().setTokenautenticacao("tok" + calls);
//                return response;
//            }
//        };
//
//        String first = provider.getToken();
//        String second = provider.getToken();
//
//        assertNotEquals(first, second);
//    }
//
//    @Test
//    void shouldExposeExpirationCheck() {
//        class TestProvider extends AnaAuthProvider {
//            TestProvider() {
//                super("user", "pass");
//            }
//
//            @Override
//            protected AnaAuthResponse fetchToken(String username, String password) {
//                AnaAuthResponse response = new AnaAuthResponse();
//                response.getItems().setTokenautenticacao("abc123");
//                return response;
//            }
//
//            boolean expired() {
//                return isTokenExpired();
//            }
//        }
//
//        TestProvider provider = new TestProvider();
//
//        assertNotNull(provider.getToken());
//        assertFalse(provider.expired());
//    }
}
