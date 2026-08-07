package com.SIMHM.provider.meteo.mapper;

import com.SIMHM.controller.response.MeteoFloodDataResponse;
import com.SIMHM.controller.response.MeteoFloodResponse;
import com.SIMHM.provider.meteo.response.DailyFloodData;
import com.SIMHM.provider.meteo.response.MeteoFloodProviderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MeteoFloodMapper {

    public MeteoFloodResponse toResponse(MeteoFloodProviderResponse providerResponse) {

        if (providerResponse == null
                || providerResponse.getDaily() == null
                || providerResponse.getDaily().getTime() == null) {

            return MeteoFloodResponse.builder()
                    .results(Collections.emptyList())
                    .build();
        }

        DailyFloodData daily = providerResponse.getDaily();

        List<MeteoFloodDataResponse> results = new ArrayList<>();

        for (int i = 0; i < daily.getTime().size(); i++) {

            results.add(
                    MeteoFloodDataResponse.builder()
                            .date(daily.getTime().get(i))
                            .riverDischarge(getValue(daily.getRiverDischarge(), i))
                            .riverDischargeMean(getValue(daily.getRiverDischargeMean(), i))
                            .riverDischargeMedian(getValue(daily.getRiverDischargeMedian(), i))
                            .build()
            );
        }

        return MeteoFloodResponse.builder()
                .results(results)
                .build();
    }

    private Double getValue(List<Double> values, int index) {

        return values != null && index < values.size()
                ? values.get(index)
                : null;
    }
}