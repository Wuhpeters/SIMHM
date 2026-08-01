package com.SIMHM.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnaStationsResponse {

    private String status;
    private Integer code;
    private String message;
    private List<AnaStation> items = new ArrayList<>();
}

