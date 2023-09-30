# QuintoPlayerAPI

# Gerenciador de Partidas e Jogadores de Futebol

Esta aplicação foi desenvolvida para simplificar o gerenciamento de partidas e jogadores de futebol em uma organização esportiva.

## Funcionalidades Principais

- **Criação de Partidas**: Crie novas partidas de futebol.
- **Listagem de Partidas**: Liste novas partidas de futebol.
- **Gerenciamento de Jogadores**: Cadastre novos jogadores.
- **Listagem de Jogadores**: Liste novos jogadores.
- **Criação de avaliação para os usuarios**: Crie novas avaliações para os usuarios que você jogou

# Tecnologias Usadas

Este aplicativo foi desenvolvido utilizando as seguintes tecnologias e ferramentas:

- **Java 17**: Linguagem de programação base para o desenvolvimento da aplicação.

- **Spring Boot**: Framework utilizado para simplificar o desenvolvimento de aplicativos Java.

- **Spring Data JPA**: Módulo do Spring para facilitar o acesso a bancos de dados relacionais usando o JPA.

- **Hibernate**: Framework de mapeamento objeto-relacional (ORM) para simplificar a persistência de dados.

- **Lombok**: Biblioteca que reduz a quantidade de código boilerplate, gerando automaticamente métodos como getters e setters.

- **Gradle**: Ferramenta de automação de construção e gerenciamento de dependências.

## Requisitos

Para executar esta aplicação, certifique-se de atender aos seguintes requisitos:

- **Java 8 ou superior**: É necessário ter pelo menos o Java 8 instalado para executar esta aplicação.

- **IDE (Ambiente de Desenvolvimento Integrado)**: Recomendamos o uso de uma IDE como o IntelliJ IDEA ou outra de sua preferência para executar esta aplicação.

- **Android Studio (para a versão móvel, se aplicável)**: Se você estiver executando a versão móvel do aplicativo, certifique-se de ter o Android Studio instalado.

- **SDK e JDK**: Para garantir o funcionamento adequado desta aplicação, verifique se possui o SDK e o JDK instalados em seu ambiente de desenvolvimento.

![image](https://github.com/Dyogo19/QuintoPlayerAPI/assets/104871305/6d005a2b-f134-40d8-912b-f52a5b64ccc3)

## Execução

Para executar este projeto, siga os passos abaixo:

1. Abra sua IDE de desenvolvimento (por exemplo, IntelliJ IDEA) e importe o projeto.

2. Execute o projeto na sua IDE pressionando o botão "Run" ou equivalente.

3. Para a versão móvel do aplicativo, abra o Android Studio.

4. Inicialize o aplicativo móvel no Android Studio seguindo as instruções específicas do projeto móvel.

Agora você pode executar tanto o projeto principal na sua IDE quanto a versão móvel do aplicativo no Android Studio.


## Conectar ao Banco de Dados

Para estabelecer uma conexão com o banco de dados, siga os passos abaixo:

1. Abra seu gerenciador de banco de dados preferido, eu utilizo o DataGrip.

2. No gerenciador de banco de dados, acesse o "Database Explorer".

3. Escolha a opção "Data Source from URL".

4. Preencha os seguintes campos:
   - **URL**: Utilize a URL do banco de dados conforme definida no arquivo `application.yaml`.
   - **Coloque esta URL "jdbc:mysql://database-1.cxlu0n4lrrwi.us-east-1.rds.amazonaws.com:3306".
   - **Driver**: Selecione o driver correspondente ao banco de dados que você estamos usando (por exemplo, MySQL).

5. Na próxima tela, preencha os campos "User" e "Password" com as credenciais fornecidas no arquivo `application.yaml`.

6. Siga os passos e confirmações necessárias para completar a configuração da conexão.

Com esses passos, você estará conectado ao banco de dados usando as informações definidas no arquivo `application.yaml`. Certifique-se de salvar suas configurações para que a conexão seja estabelecida com sucesso.

Agora você pode gerenciar e explorar o banco de dados diretamente do seu gerenciador de banco de dados.



