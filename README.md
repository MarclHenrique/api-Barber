# API Barber Shop

API REST para gerenciamento de usuários e agendamentos de serviços em uma barbearia.

## 📜 Índice
- [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📦 Instalação e Execução](#-instalação-e-execução)
- [🔑 Autenticação](#-autenticação)
- [🧑‍💼 Gerenciamento de Usuários](#-gerenciamento-de-usuários)
- [📅 Gerenciamento de Agendamentos](#-gerenciamento-de-agendamentos)
- [📂 Estrutura do Projeto](#-estrutura-do-projeto)
- [📌 Contribuição](#-contribuição)

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security (JWT)
- JPA/Hibernate
- PostegresSql
- Maven

---

## Banco de Dados Hospedado no Railway

O banco de dados PostgreSQL utilizado neste projeto está hospedado na plataforma Railway, uma solução de infraestrutura como serviço (IaaS) que facilita a criação, configuração e gestão de bancos de dados.

### Como acessar o banco de dados?

O acesso ao banco de dados PostgreSQL é feito por meio da URL de conexão fornecida pelo Railway, que inclui as informações necessárias para a conexão, como o hostname, a porta, o nome do banco de dados, o nome de usuário e a senha. Essas informações estão configuradas no arquivo `application.properties` da aplicação.

### Exemplo de Configuração no `application.properties`

```properties
# Configuração do banco de dados PostgreSQL no Railway
spring.datasource.url=jdbc:postgresql://<hostname>:<port>/<database_name>?sslmode=require
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração do JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


## 📦 Instalação e Execução

### Clone o repositório:

```sh
git clone https://github.com/seu-usuario/api-barber-shop.git
cd api-barber-shop
```


### Instale as dependências e rode o projeto:

```sh
mvn clean install
mvn spring-boot:run
```

### Acesse a API:

**URL base:** `http://localhost:8080`

---

## 🔑 Autenticação

A API usa JWT para autenticação.

### 🔹 Login (Obter Token JWT)
**POST** `/auth/login`

#### 📌 Requisição (JSON):
```json
{
  "username": "cliente1",
  "password": "123456"
}
```

#### 📌 Resposta (JSON):
```json
{
  "accessToken": "eyJhbGciOiJIUzI1..."
}
```

> **🔔 Atenção:** Todas as rotas protegidas exigem o cabeçalho `Authorization: Bearer {token}`

---

## 🧑‍💼 Gerenciamento de Usuários

### 🔹 Criar Usuário
**POST** `/api/users/register`

#### 📌 Requisição:
```json
{
  "username": "cliente1",
  "email": "cliente1@email.com",
  "password": "123456",
  "role": "CLIENTE",
  "contato": "99999-9999"
}
```

#### 📌 Resposta:
```json
{
  "id": 1,
  "username": "cliente1",
  "email": "cliente1@email.com",
  "role": "CLIENTE",
  "contato": "99999-9999"
}
```

---

## 📅 Gerenciamento de Agendamentos

### 🔹 Criar Agendamento
**POST** `/api/agendamentos/criar`

#### 📌 Requisição:
```json
{
  "dataHora": "2025-03-15T14:00:00",
  "tipoServico": "Corte de cabelo"
}
```

#### 📌 Resposta:
```json
{
  "id": 1,
  "usuarioId": 1,
  "dataHora": "2025-03-15T14:00:00",
  "tipoServico": "Corte de cabelo"
}
```

> **🚨 Atenção:** O cliente deve estar logado para criar um agendamento!

### 🔹 Listar Agendamentos do Usuário
**GET** `/api/agendamentos/meus-agendamentos`

#### 📌 Resposta:
```json
[
  {
    "id": 1,
    "dataHora": "2025-03-15T14:00:00",
    "tipoServico": "Corte de cabelo"
  }
]
```

---

## 📂 Estrutura do Projeto

```bash
api-barber-shop/
│── src/main/java/com/barber/apiBarber/
│   ├── Controller/         # Controllers da API
│   ├── Model/              # Modelos (Entidades do Banco)
│   ├── Repository/         # Interfaces do JPA
│   ├── Security/           # Configuração JWT
│   ├── Service/            # Regras de Negócio
│   ├── Config/             # Configuração da Aplicação
│   ├── BarberShopApplication.java  # Classe principal
│── src/main/resources/
│   ├── application.properties  # Configurações do Banco
│── pom.xml  # Dependências do Maven
|-- Resources
  - application.properties
```

---

## 📌 Contribuição

Se quiser contribuir com este projeto:

1. Faça um fork
2. Crie uma branch (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m "Minha nova feature"`)
4. Faça um push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request 🚀
```

