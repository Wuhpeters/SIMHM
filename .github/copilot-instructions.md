## Arquitetura do projeto
**00** - Cache Service
- Utilizar Caffeine.
- O cache é utilizado apenas para:
- Geolocalização
- Previsão do tempo
- Lista de bacias
- Lista de municípios
- Lista de estações
- Tempo de expiração:
- Geolocalização: 24 horas.
- Previsão do tempo: 2 horas.
- Lista de bacias: 24 horas.
- Lista de municípios:	24 horas.
- Lista de estações: 24 horas.

**01** - Geolocation Service
- Chama API de Geolocalização e guarda no Cache

**02** - Weather Service
- Obtém dados meteorológicos do cache ou do provider
- Retorna dados meteorológicos atuais e previsão para os próximos dias

**03** - Watershed Services
- Se tiver Cache pega info, se não pega de Location Service
- Determina a bacia hidrográfica principal da localização

**04** - Hydrology Service
- Chama API de dados hidrológicos da bacia selecionada
- Apresenta esses dados tratados

**05** - Historical Service
- Chama APIs ANA e Open-Meteo do histórico hidrológico
- Salvar tudo em um banco próprio
- Fornecer consultas históricas para os demais serviços

**06** - Risk Assessment Service
- Previsão meteorológica.
- Vazão atual.
- Nível do rio.
- Tendência da vazão.
- Histórico da região.
- Apresenta o índice de risco

### Regras de Arquitetura
- A aplicação deve seguir rigorosamente a separação de responsabilidades.
- O fluxo de dados deve seguir a ordem: Controller → Service → Provider ou Repository.
- Nenhuma camada superior pode acessar diretamente camadas inferiores.
- Toda regra de negócio deve estar na camada Service.

## Tecnologias utilizadas
- Projeto Maven:
- **Java 21**,
- **Spring Boot 4.0.7**,
- Depentencias:
- Spring Web MVC
- Spring Validation
- Spring Cache (Caffeine)
- Lombok
- Swagger / OpenAPI
- Spring Boot DevTools

## Estrutura do diretório
 SIMHM/
|-- config/
|-- controller/
   |-- request/
   |-- response/
|-- domain/
|-- exception/
|-- mapper/ 
|-- provider
    |-- ana/
        |-- auth/
        |-- exception/
        |-- hydrology/
        |-- mapper/
        |-- request/ 
        |-- response/
    |-- open-meteo/
|-- repository/
|-- service/

### Variáveis de ambiente
- Este projeto utiliza variáveis de ambiente para armazenar informações sensíveis.- Regras:
- Nunca adicionar credenciais diretamente no código-fonte.
- Sempre utilizar variáveis de ambiente para autenticação em APIs externas.
- Durante o desenvolvimento, utilizar as variáveis definidas no arquivo `.env`, quando disponível.
- O arquivo `.env` nunca deve ser versionado.
- Sempre que uma nova variável de ambiente for criada, atualizar também o arquivo `.env.example`.
- Nunca gerar ou sugerir valores reais para credenciais, tokens, senhas ou chaves de API.
- Utilizar placeholders do Spring Boot (`${NOME_DA_VARIAVEL}`) para acessar variáveis de ambiente.

## API da ANA
- As credenciais da API da ANA devem ser obtidas exclusivamente pelas variáveis `ANA_USERNAME` e `ANA_PASSWORD`.
- Nunca solicitar ao usuário que informe credenciais diretamente no código.
- Nunca criar valores padrão para as credenciais da ANA.
- Caso as credenciais não estejam configuradas, informar claramente que elas devem ser definidas 
no arquivo `.env` durante o desenvolvimento ou nas variáveis de ambiente em produção.

## Segurança
- Regras obrigatórias:
- Nunca expor senhas, tokens OAuth, API Keys ou credenciais em qualquer arquivo do projeto.
- Nunca utilizar valores fixos (hardcoded) para autenticação.
- Nunca registrar credenciais em logs.
- Toda integração com serviços externos deve utilizar variáveis de ambiente.
- Caso seja necessária uma nova configuração, documentá-la no arquivo `.env.example`.

## Integrações Externas
-Toda integração com APIs externas deve seguir as seguintes regras:
- Cada API deve possuir um Provider próprio.
- O Provider é responsável por toda comunicação HTTP e autenticação.
- As Services nunca devem conhecer detalhes da API externa.
- Credenciais devem ser obtidas exclusivamente através de variáveis de ambiente.
- Nunca duplicar código de autenticação entre Providers.

## Documentação
- Uma aplicação Back-end que consulta apis de geolocalização e previsão de tempo em tempo real, histórico pluviometrico, estações hidrologicas em tempo real
e cruza essas informações para fornecer um índice de risco de enchentes para uma determinada região.
- **API de geolocalização**: https://geocoding-api.open-meteo.com/v1/search?name={city}&count=10&language=pt&format=json&countryCode=BR
- **API de previsão de tempo**: https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,showers_sum,uv_index_max,precipitation_hours,precipitation_sum,precipitation_probability_max,et0_fao_evapotranspiration,weather_code&hourly=temperature_2m,soil_moisture_0_to_1cm,soil_moisture_1_to_3cm,soil_moisture_3_to_9cm,soil_moisture_9_to_27cm,soil_moisture_27_to_81cm,showers,rain,precipitation,precipitation_probability,relative_humidity_2m&current=temperature_2m,apparent_temperature,precipitation,rain,showers,weather_code
- **API de histórico pluviométrico**: https://archive-api.open-meteo.com/v1/archive?latitude={latitude}&longitude={longitude}&start_date={start_date}&end_date={end_date}&hourly=temperature_2m,relative_humidity_2m,rain,precipitation,weather_code,et0_fao_evapotranspiration,soil_moisture_0_to_7cm,soil_moisture_7_to_28cm,soil_moisture_28_to_100cm,soil_moisture_100_to_255cm

- **API de estações hidrológicas**: https://www.ana.gov.br/hidrowebservice/EstacoesTelemetricas/HidroInventarioEstacoes/v1?Unidade%20Federativa=RS&C%C3%B3digo%20da%20Bacia=8
- **API de histórico pluviométrico**: https://api.ana.gov.br/v1/hidroweb/series?codigoEstacao={codigoEstacao}&dataInicio={dataInicio}&dataFim={dataFim}&tipoDados=1&token={token}
- **API de autorização ANA**: https://www.ana.gov.br/hidrowebservice/EstacoesTelemetricas/OAUth/v1

### Regras
- Caso a estrutura de diretórios do diretório 'test' não estiver igual ao direrório 'com.SIMHM',
crie a estrutura necessaria para que os testes sejam criados de acordo com os diretórios.
- Sempre faça os testes unitários antes de implementar novas funcionalidades.(TDD)
- Sempre que fizer os testes, aguarde um novo comando para implementar as funcionalidades.

## Logs
- Utilizar SLF4J.
- Nunca utilizar System.out.println().
- Registrar apenas informações relevantes.
- Nunca registrar credenciais ou tokens.

## DTO

Nunca retornar entidades de domínio diretamente.

Toda entrada deve utilizar Request DTO.

Toda saída deve utilizar Response DTO.

As conversões devem ser realizadas pelos Mappers.