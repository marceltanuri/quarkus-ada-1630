# Comandos cURL para testar a API de Reclamações

Estes são exemplos de comandos `curl` para interagir com a API de Reclamações.
Execute-os em seu terminal.

## 1. Criar uma nova reclamação

Este comando cria uma nova reclamação. O corpo da requisição é um JSON com os detalhes da reclamação.
O comando `-v` (verbose) é usado para exibir detalhes da requisição e da resposta.

```bash
curl -v -X POST http://localhost:8080/reclamacoes \
-H "Content-Type: application/json" \
-d '{
  "titulo": "Produto com defeito",
  "descricao": "Recebi o produto X com defeito de fabricação.",
  "autor": "Marcel Tanuri"
}'
```

**Nota:** A resposta deste comando incluirá o ID da reclamação recém-criada. Guarde esse ID para usar nos comandos seguintes.

## 2. Listar todas as reclamações

Este comando lista todas as reclamações cadastradas.

```bash
curl -v http://localhost:8080/reclamacoes
```

## 3. Buscar uma reclamação por ID

Substitua `<ID_DA_RECLAMACAO>` pelo ID da reclamação que você deseja buscar (obtido no passo 1).

```bash
curl -v http://localhost:8080/reclamacoes/<ID_DA_RECLAMACAO>
```

## 4. Atualizar uma reclamação

Substitua `<ID_DA_RECLAMACAO>` pelo ID da reclamação que você deseja atualizar.

```bash
curl -v -X PUT http://localhost:8080/reclamacoes/<ID_DA_RECLAMACAO> \
-H "Content-Type: application/json" \
-d '{
  "titulo": "Produto com defeito - ATUALIZADO",
  "descricao": "Recebi o produto X com defeito de fabricação e a loja não quer trocar.",
  "autor": "Marcel Tanuri"
}'
```

## 5. Deletar uma reclamação

Substitua `<ID_DA_RECLAMACAO>` pelo ID da reclamação que você deseja deletar.

```bash
curl -v -X DELETE http://localhost:8080/reclamacoes/<ID_DA_RECLAMACAO>
```

## 6. Listar todas as reclamações novamente

Após a exclusão, você pode listar as reclamações novamente para confirmar que a reclamação foi removida.

```bash
curl -v http://localhost:8080/reclamacoes
```
