# Sistema backend

- Departamento de Processamento de Dados (DPD)
- Armazenamento
- Requisições
- Concorrencia
- Segurança
- Desepempenho

# Processar dados relacionados reclamações do consumidor

- Autenticação: conhecer o usuario que esta no meu sistema?
    - Validação de autenticidade: Usuario e Senha (Confirmar um dado que só ela sabe) Autenticação 1 fator
    - Autenticação Multiplos fatores - MFA (Multi factor authentication)
- Regras de negócio
- Acessível através de API Rest (HTTP, internet)
- Armazenamento de dados
    - Banco de dados: tecnologia que envolve software e hardware para armazenar dados de forma persistente. ACID, RDBMS
    - Sistema de gerenciamento de dados relacional (baseado em relacionamento de tabelas)
    - Relacionamento entre grupo de dados

=> Fulano, no dia tal reclamou da empresa X sobre Y e Z

Banco de dados de documentos

{
    "dataDeCriacao": "202603312006",
    "nomeDoConsumidor": "Fulano",
    "nomeDaEmpresa" : "X",
    "reclamacao" : "Y e Z",
    "telefone": "1119781961",
    "nome" : "Fulano",
    "email" : "fulano@test.com"
}

{
    "dataDeCriacao": "202603312006",
    "nomeDoConsumidor": "Fulano",
    "nomeDaEmpresa" : "X",
    "reclamacao" : "Y e Z",
    "anexos" : "",
    "telefone": "1119781961",
    "nome" : "Fulano",
    "email" : "fulano@test.com"
}

Banco de dados relacional

|                  Reclamacao                   |
|---DT--------|---NM----|----NE----|-----RM-----|
|202603312006 | ******  | ******   |    Y e Z   |

Tabelas do sistema: Consumidor, Empresa, Reclamacao
    

Requisitos funcionais
Requisitos Não Funcionais
