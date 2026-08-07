package com.SIMHM.service;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.request.MeteoFloodRequest;
import com.SIMHM.controller.response.MeteoFloodResponse;
import com.SIMHM.provider.meteo.exception.MeteoFloodException;
import com.SIMHM.provider.meteo.flood.MeteoFloodProvider;
import com.SIMHM.provider.meteo.mapper.MeteoFloodMapper;
import com.SIMHM.provider.meteo.response.MeteoFloodProviderResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MeteoFloodService {

    private final MeteoFloodMapper meteoFloodMapper;
    private final MeteoFloodProvider meteoFloodProvider;
    private static final Logger log = LoggerFactory.getLogger(MeteoFloodService.class);

    public MeteoFloodResponse getRiverDischarge(MeteoFloodRequest request) {

        log.info(ApplicationMessages.METEO_FLOOD_REQUEST);

        MeteoFloodProviderResponse response = meteoFloodProvider.riverDischarge(request);

        if (response == null
                || response.getDaily() == null
                || response.getDaily().getTime() == null
                || response.getDaily().getTime().isEmpty()) {

            log.warn(ApplicationMessages.METEO_FLOOD_RESPONSE_ERROR);
            throw new MeteoFloodException(ApplicationMessages.EXCEPTION_METEO_FLOOD);
        }

        log.info(ApplicationMessages.METEO_FLOOD_SUCCESS);
        return meteoFloodMapper.toResponse(response);
    }
}
