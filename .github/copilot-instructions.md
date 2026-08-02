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

### Regras
- Caso a estrutura de diretórios do diretório 'test' não estiver igual ao direrório 'com.SIMHM',
crie a estrutura necessaria para que os testes sejam criados de acordo com os diretórios.
- Sempre faça os testes unitários antes de implementar novas funcionalidades.(TDD)
- Sempre que fizer os testes, aguarde um novo comando para implementar as funcionalidades.

## Testes
- Utilizar JUnit 5.
- Utilizar Mockito quando necessário.
- Criar um teste para cada cenário.
- Seguir Given / When / Then.
- Nunca alterar código de produção apenas para fazer um teste passar.

## Logs
- Utilizar SLF4J.
- Utilizar LogMessages para mensagens padronizadas.
- Nunca utilizar System.out.println().
- Registrar apenas informações relevantes.
- Nunca registrar credenciais ou tokens.

## DTO
- Nunca retornar entidades de domínio diretamente.
- Toda entrada deve utilizar Request DTO.
- Toda saída deve utilizar Response DTO.
- As conversões devem ser realizadas pelos Mappers.

## Convenções
- Utilizar o Lombok, @Data, @Builder, @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor e @RequiredArgsConstructor sempre que possível.
- Utilizar injeção de dependência por construtor.
- Utilizar final para dependências.
- Utilizar @RequiredArgsConstructor sempre que possível.
- Evitar construtores vazios desnecessários.
- Não utilizar field injection (@Autowired em atributos).
- Métodos devem possuir responsabilidade única.
- Evitar duplicação de código.

## Providers
**Todo Provider deve:**
- Receber dependências por injeção.
- Ser responsável apenas pela comunicação HTTP.
- Nunca conter regra de negócio.
- Nunca acessar Repository.
- Utilizar HttpClient configurado em Config.
- Lançar apenas exceções específicas do Provider.

## Services
- Toda regra de negócio deve estar na Service.
- Services nunca conhecem detalhes da API externa.
- Services utilizam Providers e Repositories.
- Services nunca fazem chamadas HTTP diretamente.

## Mappers
- Providers possuem seus próprios Mappers.
- Controllers possuem seus próprios Response DTOs.
- Nunca reutilizar DTOs do Provider na Controller.
- Toda conversão deve ocorrer em Mappers.

## Exceptions
- Toda exceção deve ser específica.
- Utilizar RuntimeException.
- Nunca retornar Exception genérica.
- Todas as exceções devem ser tratadas pelo GlobalExceptionHandler.