# API de Agendamento

Este projeto é uma API REST simples para gerenciamento de clientes de um salão de cabeleireiro. Foi desenvolvido em Java com Spring Boot com foco em aprendizado de desenvolvimento backend.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.0
- Spring Web
- Spring Data JPA
- Hibernate Validator (Bean Validation)
- Banco de Dados (MySQL)
- Lombok
- JUnit 5
- Mockito
- Maven

## Como rodar o projeto localmente

### Pré-requisitos

- Java 17 instalado
- Maven instalado
- MySQL rodando (ou outro banco relacional configurado)

### Configuração do banco de dados

No arquivo `application.properties`, configure o acesso ao seu banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agendamento
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

# Para compilar e rodar o projeto:
```mvn clean install```

mvn spring-boot:run
A aplicação irá subir na porta padrão 8080. 
Exemplo: http://localhost:8080

# Endpoints disponíveis
## 1. Criar Cliente
[POST] /clientes

Body
```
{
  "nome": "Gustavo Fernandes",
  "telefone": "11999999999",
  "email": "gustavo@email.com"
}
```
Respostas possíveis

200 OK – Cliente criado com sucesso. Retorna o cliente com o ID gerado.

400 Bad Request – Se faltar algum campo obrigatório (nome, telefone, email).
