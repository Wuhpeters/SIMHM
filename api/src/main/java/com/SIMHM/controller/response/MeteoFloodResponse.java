package com.SIMHM.controller.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeteoFloodResponse {

    private List<MeteoFloodDataResponse> results;
}
