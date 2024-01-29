# SpringBoot

Este projeto representa minha jornada de aprendizado em SpringBoot, onde aplico gradualmente os conceitos adquiridos. O projeto é uma obra em constante evolução. Pretendo aprimorar e otimizar seções mais antigas conforme meu entendimento se aprofunda.

O projeto contém comentários explicativos de partes que achei interessante anotar para deixar como fonte de informação para eu mesmo futuramente

## Detalhes Técnicos:

   **IDE:** IntelliJ
   
   
   **Gerenciador de Dependências:** Gradle

   
   **Banco de Dados:** SQL

## Configuração e Utilização:

Para testar o projeto, é necessário ajustar as configurações do servidor no diretório **src/main/java/com/system/springtboot/Configuration**. Além disso, algumas configurações do aplicativo estão no arquivo application.properties.

## Estrutura de Pastas:

   **src/main/java/com/system/springboot/User:** Contém a classe do usuário, assim como DTOs (Objetos de Transferência de Dados), UserRepository e Também o AuthenticUser.

   **src/main/java/com/system/springboot/Controller:** Aqui estão definidos os endpoints do projeto, tanto os de manipulação de usuário quanto o de login.

   **src/main/java/com/system/springboot/Infra:** Contém as classes de tratamento de exceptions, a do filtro de authenticação e a geração de token.
