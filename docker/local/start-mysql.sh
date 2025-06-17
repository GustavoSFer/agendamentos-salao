#!/bin/bash

set -e

echo "============================"
echo "🔧 iniciando a construção da imagem docker da aplicação java"
echo "============================"
docker-compose build
echo ""

echo "============================"
echo "🐬 Rodando o container do MySQL..."
echo "============================"
docker-compose up -d mysql
echo ""

# Aguarda o MySQL iniciar (ajuste conforme necessário)
echo "⏳ Aguardando o MySQL iniciar (10 segundos)..."
sleep 10

echo "======================================"
echo "🚀 Executando a aplicação Java..."
echo "======================================"
docker-compose up java-app
