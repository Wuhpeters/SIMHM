package com.SIMHM.controller;

import com.SIMHM.controller.request.GeolocalizationRequest;
import com.SIMHM.controller.response.GeolocalizationResponse;
import com.SIMHM.service.GeolocalizationService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Builder
@RestController
@RequestMapping("/geolocalization")
public class GeolocalizationController {

    private final GeolocalizationService geolocalizationService;

    @GetMapping("/")
    public GeolocalizationResponse getLocalization(@RequestParam(required = false) GeolocalizationRequest localization) {
        return geolocalizationService.getLocalization(localization);
    }
}
