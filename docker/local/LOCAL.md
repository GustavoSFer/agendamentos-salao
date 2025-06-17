# Rodando a Aplicação Java com MySQL via Docker

Este guia explica como rodar a aplicação Java integrada com banco MySQL usando Docker e o script `start-mysql.sh`, para facilitar a execução sem precisar rodar o build manualmente.

---

## Pré-requisitos

- Docker e Docker Compose instalados e em funcionamento
- Permissão para executar scripts shell (`.sh`)
- Projeto com a seguinte estrutura mínima:


## Rodar o ambiente local, informe as variaveis

```declarative
DATASOURCE_DRIVE_CLASS_NAME=com.mysql.cj.jdbc.Driver
DATASOURCE_USERNAME=root
DATASOURCE_PASSWORD=123456
DATA_BASE_PLATFORM=org.hibernate.dialect.MySQLDialect
```

## Rodando o Ambiente com Docker via script
Passo 1: Dar permissão de execução ao script
``` chmod +x start-mysql.sh
``` 
Passo 2: Executar o script - entre em `cd docker/local`
``` 
./start-mysql.sh
``` 
#### Este script irá:
Construir a imagem da aplicação Java
Iniciar o container MySQL
Aguardar o MySQL iniciar
Subir o container da aplicação Java conectada ao MySQL

## Acessando a aplicação
Após os containers estarem rodando, a aplicação estará disponível em:
http://localhost:8080
