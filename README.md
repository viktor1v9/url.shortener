# 🔗 URL Shortener

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring_Boot-4.0-green?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 4.0"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL 8.0"/>
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" alt="License MIT"/>
</p>

> Um encurtador de URLs simples e eficiente desenvolvido com Java e Spring Boot. Perfeito para aprender conceitos de APIs REST, persistência de dados e boas práticas de desenvolvimento.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura](#-arquitetura)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Configuração](#-instalação-e-configuração)
  - [Clonando o Repositório](#1-clonando-o-repositório)
  - [Configuração com Docker (Recomendado)](#2-configuração-com-docker-recomendado)
  - [Configuração Local (Sem Docker)](#3-configuração-local-sem-docker)
- [Executando os Testes](#-executando-os-testes)
- [Usando a API](#-usando-a-api)
- [Licença](#-licença)
- [Agradecimentos](#-agradecimentos)

---

## 📖 Sobre o Projeto

O **URL Shortener** é uma API REST que permite encurtar URLs longas em códigos curtos e fáceis de compartilhar. Quando alguém acessa o código curto, é automaticamente redirecionado para a URL original.

### ✨ Funcionalidades

- ✅ Criar URLs encurtadas a partir de URLs longas
- ✅ Redirecionar automaticamente para a URL original
- ✅ Contagem de acessos para cada URL
- ✅ URLs com expiração automática (7 dias)
- ✅ Validação de URLs (deve começar com `http://` ou `https://`)

---

## 🏗 Arquitetura

O projeto segue uma arquitetura em camadas, muito comum em aplicações Spring Boot.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.0 | Framework para aplicações Java |
| **Spring Data JPA** | - | Persistência de dados |
| **Spring Validation** | - | Validação de dados de entrada |
| **MySQL** | 8.0 | Banco de dados relacional |
| **Lombok** | - | Redução de código boilerplate |
| **Docker** | - | Containerização |
| **Maven** | 3.9.9 | Gerenciamento de dependências |

---

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

### Para usar com Docker (Recomendado para iniciantes) 🐳

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (inclui Docker Compose)
- [Git](https://git-scm.com/downloads)

### Para rodar localmente (Sem Docker)

- [Java JDK 17](https://adoptium.net/) ou superior
- [Maven](https://maven.apache.org/download.cgi) 3.6+
- [MySQL](https://dev.mysql.com/downloads/mysql/) 8.0
- [Git](https://git-scm.com/downloads)

> 💡 **Dica para iniciantes:** Se você está começando, recomendamos usar o Docker. É mais fácil de configurar e evita problemas de compatibilidade!

---

## 🚀 Instalação e Configuração

### 1. Clonando o Repositório

Abra o terminal e execute:

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/url.shortener.git

# Entre na pasta do projeto
cd url.shortener
```

---

### 2. Configuração com Docker (Recomendado)

Esta é a forma mais fácil de rodar o projeto! 🎉

#### Passo 1: Criar o arquivo de variáveis de ambiente

Na raiz do projeto, crie um arquivo chamado `.env`:

```bash
# Windows (PowerShell)
New-Item -Name ".env" -ItemType "file"

# Linux/Mac
touch .env
```

#### Passo 2: Adicionar as variáveis no arquivo `.env`

Abra o arquivo `.env` e adicione:

```env
# Configurações do Banco de Dados
DB_USERNAME=root
DB_PASSWORD=sua_senha_segura_aqui
DB_DRIVER=com.mysql.cj.jdbc.Driver

# Configurações do JPA
JPA_DDL_AUTO=update
JPA_SHOW_SQL=false
JPA_OPEN_IN_VIEW=false
```

> ⚠️ **Importante:** Nunca compartilhe seu arquivo `.env` ou faça commit dele no Git!

#### Passo 3: Subir os containers

```bash
# Inicia o MySQL e a aplicação
docker-compose up -d

# Veja os logs (opcional)
docker-compose logs -f
```

#### Passo 4: Verificar se está funcionando

```bash
# Verificar os containers rodando
docker-compose ps
```

Você deverá ver algo assim:

```
NAME                    STATUS              PORTS
mysql_url_shortener     running (healthy)   0.0.0.0:3307->3306/tcp
url_shortener_app       running             0.0.0.0:8080->8080/tcp
```

🎉 **Pronto!** A API está disponível em `http://localhost:8080`

#### Comandos úteis do Docker

```bash
# Parar os containers
docker-compose down

# Parar e remover os volumes (apaga os dados do banco)
docker-compose down -v

# Reconstruir a imagem após alterações no código
docker-compose up -d --build

# Ver logs da aplicação
docker-compose logs -f app

# Ver logs do MySQL
docker-compose logs -f mysql
```

---

### 3. Configuração Local (Sem Docker)

Se você preferir rodar sem Docker, siga estes passos:

#### Passo 1: Instalar e configurar o MySQL

1. Instale o MySQL 8.0
2. Acesse o MySQL:

```bash
mysql -u root -p
```

3. Crie o banco de dados:

```sql
CREATE DATABASE url_shortener;
EXIT;
```

#### Passo 2: Criar o arquivo de variáveis de ambiente

Na raiz do projeto, crie um arquivo chamado `.env`:

```env
# Configurações do Servidor
SERVER_PORT=8080

# Configurações do Banco de Dados
DB_URL=jdbc:mysql://localhost:3306/url_shortener
DB_USERNAME=root
DB_PASSWORD=sua_senha_do_mysql
DB_DRIVER=com.mysql.cj.jdbc.Driver

# Configurações do JPA
JPA_DDL_AUTO=update
JPA_SHOW_SQL=true
JPA_OPEN_IN_VIEW=false
```

#### Passo 3: Executar a aplicação

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

> 💡 O arquivo `mvnw` (Maven Wrapper) permite executar o Maven sem precisar instalá-lo globalmente!

🎉 **Pronto!** A API está disponível em `http://localhost:8080`

---

## 🧪 Executando os Testes

O projeto inclui testes automatizados para garantir que tudo funciona corretamente.

### Rodar todos os testes

```bash
# Windows
.\mvnw.cmd test

# Linux/Mac
./mvnw test
```

### Rodar os testes com relatório detalhado

```bash
# Windows
.\mvnw.cmd test -Dtest=UrlServiceTests

# Linux/Mac
./mvnw test -Dtest=UrlServiceTests
```

### Ver relatório de testes

Após executar os testes, você pode ver o relatório em:
- `target/surefire-reports/`

---

## 📡 Usando a API

Agora vamos aprender a usar a API! Você pode usar **cURL** (linha de comando), **Postman** ou **Insomnia**.

### Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/urls` | Criar uma URL encurtada |
| `GET` | `/{shortCode}` | Redirecionar para a URL original |

---

### 1️⃣ Criar uma URL Encurtada

#### Usando cURL

```bash
curl -X POST http://localhost:8080/api/urls \
  -H "Content-Type: application/json" \
  -d '{"originalUrl": "https://www.google.com/search?q=spring+boot+tutorial"}'
```

**Resposta de sucesso (201 Created):**

```json
{
  "shortCode": "abc123",
  "originalUrl": "https://www.google.com/search?q=spring+boot+tutorial"
}
```

#### Usando Postman/Insomnia

1. Crie uma nova requisição
2. Selecione o método **POST**
3. URL: `http://localhost:8080/api/urls`
4. Vá na aba **Body** → selecione **raw** → **JSON**
5. Cole o JSON:

```json
{
  "originalUrl": "https://www.google.com/search?q=spring+boot+tutorial"
}
```

6. Clique em **Send**

![Exemplo no Postman](https://via.placeholder.com/600x300?text=Postman+POST+Request)

---

### 2️⃣ Acessar uma URL Encurtada

#### Usando cURL

```bash
# O -L faz o curl seguir o redirecionamento
curl -L http://localhost:8080/abc123

# Apenas ver o redirecionamento (sem seguir)
curl -I http://localhost:8080/abc123
```

**Resposta (302 Found):**

```
HTTP/1.1 302 Found
Location: https://www.google.com/search?q=spring+boot+tutorial
```

#### Usando o Navegador

Simplesmente acesse `http://localhost:8080/abc123` no seu navegador e você será redirecionado automaticamente!

#### Usando Postman/Insomnia

1. Crie uma nova requisição
2. Selecione o método **GET**
3. URL: `http://localhost:8080/abc123`
4. **Importante:** Desative a opção "Follow redirects" para ver a resposta 302
5. Clique em **Send**

---

### ⚠️ Erros Comuns

#### URL Inválida (400 Bad Request)

```bash
curl -X POST http://localhost:8080/api/urls \
  -H "Content-Type: application/json" \
  -d '{"originalUrl": "url-sem-protocolo.com"}'
```

**Resposta:**

```json
{
  "timestamp": "2025-01-08T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "URL deve começar com http:// ou https://"
}
```

#### URL Não Encontrada (404 Not Found)

```bash
curl http://localhost:8080/codigo-inexistente
```

**Resposta:**

```json
{
  "timestamp": "2025-01-08T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "URL não encontrada"
}
```

#### URL Expirada (404 Not Found)

```json
{
  "timestamp": "2025-01-08T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "URL expirou"
}
```

---

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes. Isso significa que você pode:

- ✅ Usar comercialmente
- ✅ Modificar
- ✅ Distribuir
- ✅ Usar de forma privada


---

## 🙏 Agradecimentos

Agradeço a todos que contribuíram direta ou indiretamente para este projeto:

- 💚 **Comunidade Spring** - Pela documentação incrível e framework robusto
- 🐳 **Docker** - Por simplificar a vida dos desenvolvedores
- ☕ **Comunidade Java** - Por manter a linguagem viva e evoluindo
- 📚 **Você** - Por usar e contribuir com este projeto!

---

<p align="center">
  Feito com ❤️ por <a href="https://github.com/viktor1v9">Victo Santos Dos Reis</a>
</p>

<p align="center">
  <a href="#-url-shortener">⬆️ Voltar ao topo</a>
</p>

