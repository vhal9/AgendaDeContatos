# AgendaDeContatos

Trabalho de Programação Web - Agenda de contatos

Link do vídeo 1 - CRUD: https://www.loom.com/share/fc68ba701b41457f9924f8684f0da3e7

Link do video 2 - Endereço por CEP: https://www.loom.com/share/08f43e4eac2a4a9e84cdaae7edb30595

Link do video 3 - Testes: https://www.loom.com/share/2a5ca7a791cb4845b210d4bd43cfef85
## Tecnologias:

### Frontend:
  - [VueJS v2.6.11](https://vuejs.org/)
  - [NodeJS v10.20.0](https://nodejs.org/pt-br/download/releases/)
  - [Bootstrap-vue v2.19.0](https://bootstrap-vue.org/)
### Backend:
  - [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  - [SpringBoot](https://spring.io/)
  - ApacheTomcat
  - PostgreSQL

## Configurações:

  - Crie o schema de nome agenda no usuário postgres
  - Altere o arquivo src/main/resources/application.properties:
    - Talvez seja necessário alterar a porta
    ```bash
      spring.datasource.url=jdbc:postgresql://localhost:5432/agenda
    ```
  
    - Adicione a senha do usuário postgres
    ```bash
      spring.datasource.password=
     ```
  
    - Pode ser que seja necessário trocar a instancia a seguir para create para que o Spring crie a tabela, porém somente na primeira vez que o programa executar
    ```bash
      //spring.jpa.hibernate.ddl-auto=update
      spring.jpa.hibernate.ddl-auto=create
    ```
## Para executar

### Backend

#### Importar o projeto via IntelliJ IDEA:

  - File -> Open
  - Selecione o arquivo pom.xml na pasta backend
  - Espere o IntelliJ indexar e baixar as dependencias

### Testes
  - Abra a classe src/test/java/com/AgendaDeContatos/apirest/controller/ContatoControllerTest.java
  - Botão Direito do mouse -> Run "ContratoControllerTest"

