package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyFloodData {

    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("river_discharge")
    private List<Double> riverDischarge;

    @JsonProperty("river_discharge_mean")
    private List<Double> riverDischargeMean;

    @JsonProperty("river_discharge_median")
    private List<Double> riverDischargeMedian;
}