package com.SIMHM.provider.meteo.mapper;

import com.SIMHM.controller.response.MeteoForecastDataResponse;
import com.SIMHM.controller.response.MeteoForecastResponse;
import com.SIMHM.provider.meteo.response.DailyForecastData;
import com.SIMHM.provider.meteo.response.MeteoForecastProviderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MeteoForecastMapper {

    public MeteoForecastResponse toResponse(MeteoForecastProviderResponse providerResponse) {

        if (providerResponse == null
                || providerResponse.getDaily() == null
                || providerResponse.getDaily().getTime() == null) {

            return MeteoForecastResponse.builder()
                    .results(Collections.emptyList())
                    .build();
        }

        List<MeteoForecastDataResponse> results = new ArrayList<>();

        DailyForecastData daily = providerResponse.getDaily();

        for (int i = 0; i < daily.getTime().size(); i++) {

            results.add(
                    MeteoForecastDataResponse.builder()
                            .date(daily.getTime().get(i))
                            .temperatureMax(getValue(daily.getTemperatureMax(), i))
                            .temperatureMin(getValue(daily.getTemperatureMin(), i))
                            .windGustsMax(getValue(daily.getWindGustsMax(), i))
                            .windDirectionDominant(getIntegerValue(daily.getWindDirectionDominant(), i))
                            .rainSum(getValue(daily.getRainSum(), i))
                            .showersSum(getValue(daily.getShowersSum(), i))
                            .precipitationSum(getValue(daily.getPrecipitationSum(), i))
                            .precipitationHours(getValue(daily.getPrecipitationHours(), i))
                            .precipitationProbabilityMax(
                                    getIntegerValue(daily.getPrecipitationProbabilityMax(), i))
                            .build()
            );
        }

        return MeteoForecastResponse.builder()
                .results(results)
                .build();
    }

    private Double getValue(List<Double> values, int index) {
        return values != null && index < values.size()
                ? values.get(index)
                : null;
    }

    private Integer getIntegerValue(List<Integer> values, int index) {
        return values != null && index < values.size()
                ? values.get(index)
                : null;
    }
}
