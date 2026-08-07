package com.SIMHM.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeteoForecastDataResponse {

    private String date;

    private Double temperatureMax;

    private Double temperatureMin;

    private Double windGustsMax;

    private Integer windDirectionDominant;

    private Double rainSum;

    private Double showersSum;

    private Double precipitationSum;

    private Double precipitationHours;

    private Integer precipitationProbabilityMax;

}
