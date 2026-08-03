package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocalizationLocation {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("elevation")
    private Double elevation;
    @JsonProperty("feature_code")
    private String featureCode;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("admin1_id")
    private Long admin1Id;
    @JsonProperty("admin2_id")
    private Long admin2Id;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("population")
    private Long population;
    @JsonProperty("country_id")
    private Long countryId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("admin1")
    private String admin1;
    @JsonProperty("admin2")
    private String admin2;
}
