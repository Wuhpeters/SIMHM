package com.SIMHM.config;

public final class LogMessages {

    private LogMessages() {}

    //----------ANA-AUTH----------//

    public static final String ANA_AUTH_REQUEST =
            "[ANA] Solicitando autenticação.";

    public static final String ANA_AUTH_SUCCESS =
            "[ANA] Autenticação realizada com sucesso.";

    public static final String ANA_AUTH_ERROR =
            "[ANA] Erro ao autenticar na API da ANA.";

    public static final String ANA_TOKEN_EXPIRED =
            "[ANA] Token expirado. Solicitando novo token.";

    //----------ANA-STATIONS----------//

    public static final String ANA_STATIONS_REQUEST =
            "[ANA] Consultando inventário de estações.";

    public static final String ANA_STATIONS_SUCCESS =
            "[ANA] Inventário de estações recebido com sucesso.";

    public static final String ANA_STATIONS_ERROR =
            "[ANA] Erro ao consultar inventário de estações.";

}
