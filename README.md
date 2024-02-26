# Desafio Back-End - API de Planetas Star Wars

Bem-vindo ao desafio Back-End para criar uma API de Planetas Star Wars! Este projeto visa fornecer uma API RESTful para gerenciar informações sobre planetas do universo Star Wars, incluindo nome, clima, terreno e a quantidade de aparições em filmes.

## Funcionalidades
A API  fornece as seguintes funcionalidades:

- Adicionar um planeta: Permitir a adição de um novo planeta especificando nome, clima e terreno.
- Listar planetas: Listar todos os planetas cadastrados.
- Buscar por nome: Buscar um planeta pelo seu nome.
- Buscar por ID: Buscar um planeta pelo seu ID único.
- Remover planeta: Remover um planeta do banco de dados.

## Documentação da API
A documentação da API pode ser acessada através do Swagger. Execute o projeto e acesse o seguinte endpoint no navegador para visualizar a documentação:

http://localhost:8080/swagger-ui.html


## Tecnologias Utilizadas
- Linguagens: Java.
- Banco de Dados: PostgreSQL.



## Endpoints

A API possui os seguintes endpoints:

- **POST /planetas:**
    - **Operação:** Salva um novo planeta.
    - **Descrição:** Adiciona um novo planeta ao banco de dados.
    - **Parâmetros de Entrada:** Objeto JSON representando o planeta a ser adicionado.
    - **Respostas:**
        - Código 200: Salvo com sucesso.

- **GET /planetas/all:**
    - **Operação:** Lista todos os Planetas.
    - **Descrição:** Retorna uma lista com todos os planetas cadastrados no banco de dados.
    - **Parâmetros de Saída:** Lista de objetos JSON representando os planetas.
    - **Respostas:**
        - Código 200: Retorna todos os planetas.

- **GET /planetas:**
    - **Operação:** Busca um planeta por nome ou id.
    - **Descrição:** Busca um planeta pelo seu nome ou ID único.
    - **Parâmetros de Entrada:**
        - `name` (opcional): Nome do planeta a ser buscado.
        - `id` (opcional): ID único do planeta a ser buscado.
    - **Respostas:**
        - Código 200: Planeta encontrado.
        - Código 404: Planeta não encontrado.

- **DELETE /planetas:**
    - **Operação:** Deleta um planeta por id.
    - **Descrição:** Remove um planeta do banco de dados com base no seu ID único.
    - **Parâmetros de Entrada:**
        - `id`: ID único do planeta a ser deletado.
    - **Respostas:**
        - Código 200: Planeta deletado.
        - Código 400: Planeta não deletado.

## Licença
Este projeto está licenciado sob a MIT License.