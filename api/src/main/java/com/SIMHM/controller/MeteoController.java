package com.SIMHM.controller;

import com.SIMHM.controller.request.MeteoFloodRequest;
import com.SIMHM.controller.response.MeteoFloodResponse;
import com.SIMHM.service.MeteoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Builder
@RestController
@RequestMapping("/meteo")
public class MeteoController {

    private final MeteoService meteoService;

    @GetMapping("/flood")
    public MeteoFloodResponse getLocalization(MeteoFloodRequest localization) {
        return meteoService.getRiverDischarge(localization);
    }
}
