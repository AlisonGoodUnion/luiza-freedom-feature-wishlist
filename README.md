# luiza-freedom-feature-wishlist

### Freedom - Teste Backend:
    Objetivo do projeto é desenvolver uma feature de Wishlist, desafio proposto pelo time freedom. 

Serviço  de Wishlist

    - Linguagem Java 17
    - Mongodb
    - Docker: docker-compose.yml
    - Gradle
    - Lombok

### Funcionalidades:

    - Adicionar um produto na Wishlist do cliente;
    - Remover um produto da Wishlist do cliente;
    - Consultar todos os produtos da Wishlist do cliente;
    - Consultar se um determinado produto está na Wishlist do
    cliente;


## Dicas de utilizacao do projeto:

    - Instalar JDK, Docker
    - Start MongoDB docker-compose: 
        docker-compose -f docker-compose.yml up
        docker-compose up -d
        Dica: executar em algum prompt ou pelo próprio painel do docker.
    - Executar projeto springboot.

## API DOC:
http://localhost:8080/swagger-ui/index.html

### Arquitetura do projeto baseada na clean architecture:
#### Packages Information :
#### Conteúdo das funcionalidades/usecases: [customer(wishlist),product]
#### Domínio do negócio. [domain]
#### Classes de modelo, entidades que representam o domínio. [model]
#### Classes de serviço que representam o domínio. [service]
#### Classes de acesso a dados. [infrastructure]
#### Classes de acesso a dados externos. [client]
#### Classes de acesso a dados banco. [persistence]
#### Classes responsáveis pelas operações na API. [presentation]
#### Classes de acesso a dados. [infrastructure]
#### Classes responsáveis pelas operações em banco. [persistence]
#### Solicitações de operações HTTP [presentation]


