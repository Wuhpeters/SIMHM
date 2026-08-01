package com.SIMHM.provider.ana.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnaAuthResponse {

    private String status;
    private Integer code;
    private String message;
    private AnaAuthItemsResponse items;
}
