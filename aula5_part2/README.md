# Desafio: API de Reclamações com Quarkus e Integração Externa

## Objetivo

Construir uma API RESTful que simule um sistema de reclamações, similar ao "Reclame Aqui". A API será desenvolvida com Quarkus e deverá incluir a criação e listagem de reclamações, além de se integrar a uma API externa para gerar títulos de forma automática.

## Requisitos Técnicos

*   **Framework:** Quarkus
*   **Linguagem:** Java
*   **Gerenciamento de Dependências:** Maven
*   **Extensões Quarkus (sugestão):**
    *   `quarkus-resteasy-reactive-jackson` (para os endpoints REST)
    *   `quarkus-rest-client-reactive-jackson` (para o cliente REST da API externa)

## Modelo de Dados: `Reclamacao`

Crie uma classe `Reclamacao` para representar uma reclamação no sistema. Ela deve conter os seguintes campos:

*   `id` (String): Identificador único da reclamação.
*   `titulo` (String): Título da reclamação.
*   `descricao` (String): O relato/descrição detalhada da reclamação.
*   `autor` (String): Nome da pessoa que abriu a reclamação.

## Arquitetura

Para uma boa organização do código, a aplicação deve ser dividida em camadas:

1.  **`ReclamacaoResource`**: A classe para os endpoints REST. Ela deve ser o ponto de entrada das requisições, delegando a lógica para a camada de serviço.
2.  **`ReclamacaoService`**: A classe de serviço com a lógica de negócio. Será responsável por gerenciar a lista de reclamações e orquestrar a chamada à API externa.
3.  **`TituloGeneratorClient`**: Uma interface de cliente REST (usando MicroProfile REST Client) para se comunicar com a API externa de geração de títulos.

## Endpoints da API

A API deve expor os seguintes endpoints no caminho base `/reclamacoes`:

---

### 1. Criar uma nova reclamação

*   **Método:** `POST`
*   **Endpoint:** `/reclamacoes`
*   **Content-Type:** `application/json`
*   **Corpo da Requisição (Request Body):** Um objeto JSON contendo `descricao` e `autor`. O campo `titulo` é opcional.

    **Exemplo 1 (com título):**
    ```json
    {
      "titulo": "Produto não entregue",
      "descricao": "Fiz uma compra há 2 semanas e o produto ainda não foi entregue.",
      "autor": "Maria Souza"
    }
    ```

    **Exemplo 2 (sem título):**
    ```json
    {
      "descricao": "O aplicativo de celular está travando constantemente e fechando sozinho.",
      "autor": "Carlos Pereira"
    }
    ```

*   **Regras de Negócio:**
    *   O `id` da reclamação deve ser gerado automaticamente pelo servidor (ex: `UUID.randomUUID().toString()`).
    *   Se o campo `titulo` **não for enviado** (ou for enviado em branco) no corpo da requisição, a API deverá consumir um serviço externo para gerar um título automaticamente.
*   **Respostas Esperadas:**
    *   **`201 Created`**: Em caso de sucesso, retornar o status `201` e o objeto completo da reclamação criada (incluindo `id` e o `titulo` - original ou gerado) no corpo da resposta.

---

### 2. Listar todas as reclamações

*   **Método:** `GET`
*   **Endpoint:** `/reclamacoes`
*   **Content-Type:** `application/json`
*   **Resposta Esperada:**
    *   **`200 OK`**: Retornar um array de objetos JSON com todas as reclamações cadastradas. Se não houver reclamações, retornar um array vazio `[]`.

## Integração com API Externa para Geração de Títulos

Para a geração automática de títulos, você deve criar um cliente REST que interaja com uma API pública.

*   **API Sugerida:** `https://loripsum.net/api`
*   **Endpoint a ser consumido:** `GET /1/short/plaintext`
*   **Descrição:** Este endpoint retorna um parágrafo curto de "Lorem Ipsum" em formato de texto plano, que servirá como nosso título gerado.

**Implementação:**

1.  Crie uma interface `TituloGeneratorClient` anotada com `@RegisterRestClient`.
2.  Configure a `baseUri` da interface para `https://loripsum.net/api`.
3.  Declare um método na interface que faça uma requisição `GET` para o path `/1/short/plaintext` e que produza `MediaType.TEXT_PLAIN`.
4.  Injete este cliente na sua `ReclamacaoService` usando `@Inject` e `@RestClient`.
5.  Na lógica de criação de reclamação, chame o método do cliente quando o título não for fornecido.

## Desafios Bônus

1.  **Testes:** Crie testes de integração para o `ReclamacaoResource` que verifiquem todos os cenários, incluindo a criação de uma reclamação com e sem título.
2.  **Configuração da URL:** Em vez de "hardcodar" a URL da API externa na anotação `@RegisterRestClient`, mova-a para o arquivo `application.properties`. (Dica: use `br.com.marceltanuri.reclameaqui.client.TituloGeneratorClient/mp-rest/url=https://loripsum.net/api`).
3.  **Tratamento de Erros:** O que acontece se a API externa estiver fora do ar? Adicione um tratamento de erro (por exemplo, um `try-catch`) na chamada ao cliente REST e defina um título padrão caso a chamada falhe.

Bom trabalho!
