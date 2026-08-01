package com.SIMHM.provider.ana.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnaAuthRequest {

    private String username;
    private String password;

    public AnaAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
