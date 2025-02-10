# Teste para Pessoa Desenvolvedor Java

## Descrição

Este teste tem como objetivo avaliar suas habilidades no desenvolvimento de uma API RESTful utilizando Java com Maven, seguindo o padrão arquitetural MVC e persistindo os dados em um banco de dados H2.

A aplicação deve conter dois endpoints:
- **Cadastro de Pessoas** (POST)
- **Consulta de Pessoas** (GET)

## Requisitos do Projeto

### Tecnologias Utilizadas
- Java (versão 11 ou superior)
- Spring Boot
- Maven
- Banco de Dados H2
- Padrão Arquitetural MVC
- UUID para identificador

### Funcionalidades da API

#### 1. Cadastro de Pessoas (POST `/pessoas`)
- Deve receber um objeto JSON contendo `nome` e `endereco`.
- O ID deve ser gerado automaticamente como um UUID.
- Retornar o objeto cadastrado com status **201 Created**.

**Exemplo de Request:**
```json
{
  "nome": "João da Silva",
  "endereco": "Rua Exemplo, 123 - São Paulo"
}
```

**Exemplo de Response:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "João da Silva",
  "endereco": "Rua Exemplo, 123 - São Paulo"
}
```

#### 2. Consulta de Pessoas (GET `/pessoas/{id}`)
- Deve receber um ID como **Path Parameter**.
- Retornar o objeto correspondente se existir, com status **200 OK**.
- Caso não encontrado, retornar status **404 Not Found**.

**Exemplo de Request:**
```
GET /pessoas/550e8400-e29b-41d4-a716-446655440000
```

**Exemplo de Response:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "João da Silva",
  "endereco": "Rua Exemplo, 123 - São Paulo"
}
```

Caso o ID não seja encontrado:
```json
{
  "erro": "Pessoa não encontrada"
}
```

## Regras de Implementação
1. Utilize **Spring Boot** para desenvolver a API.
2. Siga o padrão **MVC** (Model-View-Controller).
3. Utilize **Maven** para gestão de dependências.
4. O banco de dados deve ser **H2**, com dados persistidos.
5. O ID deve ser gerado automaticamente com **UUID**.

## Repositório e Versionamento
1. O código deve ser commitado no **mesmo repositório** onde este teste está localizado.
2. Criar uma nova branch com prefixo `feature/`, exemplo: `feature/projeto-api`.

## Entrega
Ao finalizar o desenvolvimento:
1. Suba o código para o repositório na branch criada.
2. Abra um Pull Request para revisão.

## Apresentação
Ao finalizar a entrega do projeto, irá acontecer uma reunião simulando um onboarding técnico onde você irá apresentar o código para uma pessoa desenvolvedora júnior para continuar as atividades.

Boa sorte! 🚀

