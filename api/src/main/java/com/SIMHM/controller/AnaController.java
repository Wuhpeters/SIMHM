package com.SIMHM.controller;

import com.SIMHM.controller.response.AnaStationsResponse;
import com.SIMHM.service.StationInvetaryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Builder
@RestController
@RequestMapping("/ana")
public class AnaController {

    private final StationInvetaryService stationInvetaryService;

    @GetMapping("/stations")
    public AnaStationsResponse stations() {
        return stationInvetaryService.getStations();
    }

}
