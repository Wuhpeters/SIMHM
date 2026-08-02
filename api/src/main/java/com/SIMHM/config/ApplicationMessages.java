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

    //----------EXCEPTION-MESSAGES----------//

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
}
