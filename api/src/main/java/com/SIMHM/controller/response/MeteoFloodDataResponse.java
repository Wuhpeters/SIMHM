package com.SIMHM.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeteoFloodDataResponse {

    private String date;
    private Double riverDischarge;
    private Double riverDischargeMean;
    private Double riverDischargeMedian;

}