# 📚 Gerenciamento de Livros API

API REST desenvolvida em **Java** com Spring Boot para cadastro e gerenciamento de livros. Projeto desenvolvido durante a graduação em **Engenharia de Software na Universidade de Vassouras**.

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Web
- Maven
- JUnit 5

## 📋 Sobre o projeto

O projeto consiste no desenvolvimento de uma API REST para gerenciamento de livros, permitindo realizar operações de cadastro, consulta, atualização e exclusão de registros.

A aplicação foi desenvolvida com o objetivo de praticar conceitos de desenvolvimento de APIs utilizando **Java e Spring Boot**, incluindo a criação de controllers, services e endpoints REST.

Atualmente, os dados são armazenados em memória, utilizando uma lista de objetos Java.

## ⚙️ Funcionalidades

- [x] Cadastrar livro
- [x] Listar livros
- [x] Buscar livro por ID
- [x] Atualizar livro
- [x] Excluir livro
- [x] Gerar IDs automaticamente

## 🔗 Endpoints

| Método | Endpoint | Descrição |
|:------:|----------|-----------|
| `GET` | `/livro` | Lista todos os livros |
| `GET` | `/livro/{id}` | Busca um livro pelo ID |
| `POST` | `/livro` | Cadastra um novo livro |
| `PUT` | `/livro/{id}` | Atualiza um livro |
| `DELETE` | `/livro/{id}` | Remove um livro |

## 📥 Exemplo de cadastro

### Requisição

```http
POST /livro
Content-Type: application/json

{
  "titulo": "O Hobbit",
  "autor": "J. R. R. Tolkien",
  "genero": "Fantasia",
  "anoPublicacao": 1937
}
```
### Resposta esperada
HTTP 201 Created

```
{
  "id": 1,
  "titulo": "O Hobbit",
  "autor": "J. R. R. Tolkien",
  "genero": "Fantasia",
  "anoPublicacao": 1937
}
```



