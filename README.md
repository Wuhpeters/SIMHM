## Documentação
- Uma aplicação Back-end que consulta apis de geolocalização e previsão de tempo em tempo real, histórico pluviometrico, 
estações hidrologicas em tempo real e cruza essas informações para fornecer um índice de risco de enchentes para uma determinada região.

## Estrutura do diretório
```text
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
```
## Tecnologias utilizadas
- Projeto Maven:
- Java 21,
- Spring Boot 4.0.7,
- Depentencias:
- Spring Web MVC
- Spring Validation
- Spring Cache (Caffeine)
- Lombok
- Swagger / OpenAPI
- Spring Boot DevTools
