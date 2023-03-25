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
    - Start MongoDB docker-compose: docker-compose -f docker-compose.yml up
        Dica: executar em algum prompt ou pelo próprio painel do docker.
    - Executar projeto springboot.

## API DOC:
http://localhost:8080/swagger-ui/index.html

### Arquitetura do projeto baseada na clean architecture:
#### Packages Information :
#### Conteúdo da funcionalidade: [customer](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer).
#### Domínio do negócio. [domain](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fdomain).
#### Classes de modelo, entidades que representam o domínio. [model](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fdomain%2Fmodel).
#### Classes de serviço que representam o domínio. [service](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fdomain%2Fservice).
#### Classes de acesso a dados. [infrastructure](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Finfrastructure).
#### Classes de acesso a dados externos. [client](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Finfrastructure%2Fclient).
#### Classes de acesso a dados banco. [persistence](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Finfrastructure%2Fpersistence).
#### Classes responsáveis pelas operações na API. [presentation](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fpresentation)[customer](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer)
#### Classes de acesso a dados. [infrastructure](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Finfrastructure).
#### Classes responsáveis pelas operações em banco. [persistence](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fpersistence).
#### Solicitações de operações HTTP [presentation](src%2Fmain%2Fjava%2Fcom%2Fluiza%2Fdemo%2Fcustomer%2Fpresentation).


