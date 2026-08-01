package com.SIMHM.provider.ana.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnaHydrologyStationsRequest {

    private String state;
    private String basinCode;
}
