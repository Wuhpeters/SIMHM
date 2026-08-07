package com.SIMHM.provider.meteo.mapper;

import com.SIMHM.controller.response.MeteoHistoricalWeatherDataResponse;
import com.SIMHM.controller.response.MeteoHistoricalWeatherResponse;
import com.SIMHM.provider.meteo.response.MeteoHistoricalWeatherProviderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MeteoHistoricalWeatherMapper {

    public MeteoHistoricalWeatherResponse toResponse(
            MeteoHistoricalWeatherProviderResponse providerResponse) {

        if (providerResponse == null
                || providerResponse.getDaily() == null
                || providerResponse.getDaily().getTime() == null) {

            return MeteoHistoricalWeatherResponse.builder()
                    .results(Collections.emptyList())
                    .build();
        }

        List<MeteoHistoricalWeatherDataResponse> results = new ArrayList<>();

        for (int i = 0; i < providerResponse.getDaily().getTime().size(); i++) {

            results.add(
                    MeteoHistoricalWeatherDataResponse.builder()
                            .date(providerResponse.getDaily().getTime().get(i))
                            .precipitationSum(getValue(
                                    providerResponse.getDaily().getPrecipitationSum(), i))
                            .temperatureMean(getValue(
                                    providerResponse.getDaily().getTemperature2mMean(), i))
                            .rainSum(getValue(
                                    providerResponse.getDaily().getRainSum(), i))
                            .precipitationHours(getValue(
                                    providerResponse.getDaily().getPrecipitationHours(), i))
                            .build()
            );
        }

        return MeteoHistoricalWeatherResponse.builder()
                .results(results)
                .build();
    }

    private Double getValue(List<Double> values, int index) {

        return values != null && index < values.size()
                ? values.get(index)
                : null;
    }
}
