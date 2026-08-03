package com.SIMHM.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeolocalizationResponse {

    private String latitude;
    private String longitude;
    private String city;
    private String state;
    private String country;
}
