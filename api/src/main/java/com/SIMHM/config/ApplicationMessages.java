package com.SIMHM.config;

public final class ApplicationMessages {

    private ApplicationMessages() {}

    //----------ANA-AUTH----------//

    public static final String ANA_AUTH_REQUEST =
            "[ANA] Solicitando autenticação.";

    public static final String ANA_AUTH_SUCCESS =
            "[ANA] Autenticação realizada com sucesso.";

    public static final String ANA_AUTH_HTTP_ERROR =
            "[ANA] Erro HTTP durante autenticação.";

    public static final String ANA_AUTH_RESPONSE_ERROR =
            "[ANA] Erro ao converter resposta da autenticação.";

    public static final String ANA_AUTH_INTERRUPTED =
            "[ANA] Requisição de autenticação interrompida.";

    public static final String ANA_TOKEN_EXPIRED =
            "[ANA] Token expirado. Gerando novo token.";

    public static final String ANA_TOKEN_REUSED =
            "[ANA] Utilizando token já existente.";

    //----------ANA-STATIONS----------//

    public static final String ANA_STATIONS_REQUEST =
            "[ANA] Consultando inventário de estações.";

    public static final String ANA_STATIONS_SUCCESS =
            "[ANA] Inventário de estações recebido com sucesso.";

    public static final String ANA_STATIONS_HTTP_ERROR =
            "[ANA] Erro HTTP ao consultar inventário de estações.";

    public static final String ANA_STATIONS_RESPONSE_ERROR =
            "[ANA] Erro ao converter resposta do inventário de estações.";

    public static final String ANA_STATIONS_INTERRUPTED =
            "[ANA] Consulta de estações interrompida.";

    //----------ANA-STATIONS-INVENTARY-SERVICE----------//

    public static final String STATION_SERVICE_REQUEST =
            "[SERVICE] Buscando inventário de estações.";

    public static final String STATION_SERVICE_SUCCESS =
            "[SERVICE] Inventário de estações retornado com sucesso.";

    public static final String STATION_SERVICE_EMPTY =
            "[SERVICE] Nenhuma estação encontrada.";

    //----------ANA-EXCEPTION-MESSAGES----------//

    public static final String EXCEPTION_ANA_AUTH =
            "Falha ao autenticar na API da ANA.";

    public static final String EXCEPTION_ANA_AUTH_TOKEN =
            "Falha ao obter token de autenticação da ANA.";

    public static final String EXCEPTION_ANA_AUTH_RESPONSE =
            "Erro ao processar resposta da autenticação da ANA.";

    public static final String EXCEPTION_ANA_AUTH_INTERRUPTED =
            "Requisição de autenticação da ANA foi interrompida.";

    public static final String EXCEPTION_ANA_STATIONS_REQUEST =
            "Requisição de consulta de estações não pode ser nula.";

    public static final String EXCEPTION_ANA_STATIONS =
            "Falha ao consultar estações hidrológicas da ANA.";

    public static final String EXCEPTION_ANA_STATIONS_RESPONSE =
            "Erro ao processar resposta das estações da ANA.";

    public static final String EXCEPTION_ANA_STATIONS_INTERRUPTED =
            "Consulta de estações da ANA foi interrompida.";

    public static final String EXCEPTION_STATION_NOT_FOUND =
            "Nenhuma estação foi encontrada para os parâmetros informados.";

    //----------METEO-GEOLOCATION----------//

    public static final String METEO_GEOLOC_REQUEST_INVALID =
            "[METEO] Requisição de geolocalização inválida.";

    public static final String METEO_GEOLOC_REQUEST =
            "[METEO] Solicitando geolocalização.";

    public static final String METEO_GEOLOC_SUCCESS =
            "[METEO] Geolocalização retornada com sucesso.";

    public static final String METEO_GEOLOC_HTTP_ERROR =
            "[METEO] Erro HTTP ao consultar geolocalização.";

    public static final String METEO_GEOLOC_RESPONSE_ERROR =
            "[METEO] Erro ao processar resposta da geolocalização.";

    public static final String METEO_GEOLOC_INTERRUPTED =
            "[METEO] Requisição de geolocalização interrompida.";

    //----------METEO-EXCEPTION-MESSAGES----------//

    public static final String EXCEPTION_METEO_GEOLOC =
            "Falha ao consultar geolocalização.";

    //----------METEO-FLOOD----------//

    public static final String METEO_FLOOD_REQUEST =
            "[METEO] Solicitando dados de vazão do rio.";

    public static final String METEO_FLOOD_SUCCESS =
            "[METEO] Dados de vazão do rio retornados com sucesso.";

    public static final String METEO_FLOOD_HTTP_ERROR =
            "[METEO] Erro HTTP ao consultar dados de vazão do rio.";

    public static final String METEO_FLOOD_RESPONSE_ERROR =
            "[METEO] Erro ao processar resposta de vazão do rio.";

    public static final String METEO_FLOOD_INTERRUPTED =
            "[METEO] Requisição de dados de vazão do rio interrompida.";

    //----------METEO-FLOOD-EXCEPTION-MESSAGES----------//

    public static final String EXCEPTION_METEO_FLOOD =
            "Falha ao consultar dados de vazão do rio.";

    public static final String EXCEPTION_METEO_FLOOD_RESPONSE =
            "Erro ao processar resposta de vazão do rio.";

    public static final String EXCEPTION_METEO_FLOOD_INTERRUPTED =
            "Requisição de dados de vazão do rio foi interrompida.";

    public static final String EXCEPTION_METEO_FLOOD_REQUEST_INVALID =
            "Requisição de dados de vazão do rio é inválida ou incompleta.";

    //----------METEO-HISTORICAL-WEATHER----------//

    public static final String METEO_HISTORICAL_WEATHER_REQUEST =
            "[METEO] Solicitando dados meteorológicos históricos.";

    public static final String METEO_HISTORICAL_WEATHER_SUCCESS =
            "[METEO] Dados meteorológicos históricos retornados com sucesso.";

    public static final String METEO_HISTORICAL_WEATHER_HTTP_ERROR =
            "[METEO] Erro HTTP ao consultar dados meteorológicos históricos.";

    public static final String METEO_HISTORICAL_WEATHER_RESPONSE_ERROR =
            "[METEO] Erro ao processar resposta dos dados meteorológicos históricos.";

    public static final String METEO_HISTORICAL_WEATHER_INTERRUPTED =
            "[METEO] Requisição de dados meteorológicos históricos interrompida.";


//----------METEO-HISTORICAL-WEATHER-EXCEPTION-MESSAGES----------//

    public static final String EXCEPTION_METEO_HISTORICAL_WEATHER =
            "Falha ao consultar dados meteorológicos históricos.";

    public static final String EXCEPTION_METEO_HISTORICAL_WEATHER_RESPONSE =
            "Erro ao processar resposta dos dados meteorológicos históricos.";

    public static final String EXCEPTION_METEO_HISTORICAL_WEATHER_INTERRUPTED =
            "Requisição de dados meteorológicos históricos foi interrompida.";

    public static final String EXCEPTION_METEO_HISTORICAL_WEATHER_REQUEST_INVALID =
            "Requisição de dados meteorológicos históricos é inválida ou incompleta.";

    //----------METEO-FORECAST----------//

    public static final String METEO_FORECAST_REQUEST =
            "[METEO] Solicitando previsão do tempo.";

    public static final String METEO_FORECAST_SUCCESS =
            "[METEO] Previsão do tempo retornada com sucesso.";

    public static final String METEO_FORECAST_HTTP_ERROR =
            "[METEO] Erro HTTP ao consultar previsão do tempo.";

    public static final String METEO_FORECAST_RESPONSE_ERROR =
            "[METEO] Erro ao processar resposta da previsão do tempo.";

    public static final String METEO_FORECAST_INTERRUPTED =
            "[METEO] Requisição da previsão do tempo interrompida.";

//----------METEO-FORECAST-EXCEPTION-MESSAGES----------//

    public static final String EXCEPTION_METEO_FORECAST =
            "Falha ao consultar previsão do tempo.";

    public static final String EXCEPTION_METEO_FORECAST_RESPONSE =
            "Erro ao processar resposta da previsão do tempo.";

    public static final String EXCEPTION_METEO_FORECAST_INTERRUPTED =
            "Requisição da previsão do tempo foi interrompida.";

    public static final String EXCEPTION_METEO_FORECAST_REQUEST_INVALID =
            "Requisição da previsão do tempo é inválida ou incompleta.";
}

