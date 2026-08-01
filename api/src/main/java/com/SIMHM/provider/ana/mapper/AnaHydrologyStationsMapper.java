package com.SIMHM.provider.ana.mapper;

import com.SIMHM.provider.ana.exception.AnaHydrologyStationsException;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AnaHydrologyStationsMapper {

    private final ObjectMapper objectMapper;

    public AnaHydrologyStationsResponse toResponse(String json) {
        try {
            return objectMapper.readValue(json, AnaHydrologyStationsResponse.class);
        } catch (IOException e) {
            throw new AnaHydrologyStationsException(
                    "Erro ao converter resposta da ANA.", e);
        }
    }
}