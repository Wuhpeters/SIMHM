package com.SIMHM.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeteoHistoricalWeatherRequest {

    private String latitude;
    private String longitude;
    private String startDate;
    private String endDate;
}
