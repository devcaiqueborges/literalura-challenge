# 📚 LiterAlura - Catálogo de Livros

O **LiterAlura** é um sistema de catálogo de livros que consome a API Gutendex, processa dados em formato JSON e os armazena em um banco de dados relacional (PostgreSQL). Este projeto foi desenvolvido como desafio final do programa Oracle Next Education (ONE) em parceria com a Alura.

## 🚀 Funcionalidades
* **Busca Dinâmica:** Pesquisa livros por título diretamente na API Gutendex.
* **Persistência de Dados:** Armazena livros e autores de forma relacionada no PostgreSQL.
* **Filtros Avançados:**  Listagem de livros e autores cadastrados.
    * Busca de autores vivos em um determinado ano.
    * Filtro de livros por idioma (PT, EN, ES, FR).

## 🛠️ Tecnologias Utilizadas
* **Java 21:** Linguagem principal (utilizando Records e Streams).
* **Spring Boot 3.1:** Framework para gerenciamento da aplicação.
* **Spring Data JPA:** Abstração para persistência de dados.
* **PostgreSQL:** Banco de Dados Relacional.
* **Jackson:** Biblioteca para desserialização de JSON.

## 📋 Como Executar
1. Clone este repositório.
2. Certifique-se de ter o **PostgreSQL** instalado e crie um banco chamado `literalura`.
3. Configure seu usuário e senha no arquivo `src/main/resources/application.properties`.
4. Execute a classe `LiteraluraApplication`.

## 👨‍💻 Desenvolvedor
**Carlos Caique Borges de Sousa** | *Developer Back-End Java/Spring Boot.*