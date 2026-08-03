package com.SIMHM.provider.meteo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeteoGeolocalizationRequest {

    private String city;
}
