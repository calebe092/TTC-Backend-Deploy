# ⚡ EletroLight — Backend REST API (Spring Boot)

Backend Java da plataforma **EletroLight**, responsável pelo gerenciamento de usuários, anúncios, mensagens (chat), denúncias, conteúdo educativo e pontos de coleta. Exposto como uma API REST consumida pelo frontend HTML/JS.

> **Framework**: Spring Boot 4.0.6  
> **Linguagem**: Java 21  
> **Banco de Dados**: PostgreSQL (via Spring Data JPA / Hibernate)  
> **Segurança**: Spring Security com BCrypt para hash de senhas  
> **Deploy**: Railway (`ttc-backend-deploy-production.up.railway.app`)

---

## 📋 Sumário

1. [Visão Geral](#visão-geral)
2. [Tecnologias e Dependências](#tecnologias-e-dependências)
3. [Arquitetura do Projeto](#arquitetura-do-projeto)
4. [Entidades e Relacionamentos](#entidades-e-relacionamentos)
5. [Controllers e Endpoints REST](#controllers-e-endpoints-rest)
6. [DTOs](#dtos)
7. [Como Executar](#como-executar)
8. [Configuração do Banco](#configuração-do-banco)
9. [Segurança e CORS](#segurança-e-cors)
10. [Notas para Desenvolvedores](#notas-para-desenvolvedores)

---

## 🎯 Visão Geral

Este projeto implementa a camada de backend da plataforma EletroLight, fornecendo endpoints REST para todas as operações do sistema:

- **Autenticação e Cadastro** — registro com validação de CPF, login por email/CPF, alteração de senha
- **Anúncios** — CRUD completo com moderação (aprovação/rejeição)
- **Chat** — mensagens entre usuários vinculadas a anúncios
- **Denúncias** — registro e resolução de denúncias com sistema de punições
- **Conteúdo Educativo** — gerenciamento de textos/vídeos exibidos na home
- **Pontos de Coleta** — locais de descarte em Manaus consumidos pelo mapa Leaflet
- **Administração** — painel de moderação com controle de usuários e restrições

---

## 🛠️ Tecnologias e Dependências

| Dependência | Versão | Finalidade |
|-------------|--------|------------|
| `spring-boot-starter-web` | 4.0.6 | REST API e servidor embutido (Tomcat) |
| `spring-boot-starter-data-jpa` | 4.0.6 | Acesso ao banco via JPA / Hibernate |
| `spring-boot-starter-validation` | 4.0.6 | Validação de DTOs (`@Valid`, `@NotBlank`) |
| `spring-boot-starter-security` | 4.0.6 | Configuração de segurança e CORS |
| `spring-security-crypto` | — | BCryptPasswordEncoder para hash de senhas |
| `spring-boot-starter-websocket` | 4.0.6 | Suporte a WebSocket (configurado, extensível) |
| `spring-boot-devtools` | 4.0.6 | Hot reload em desenvolvimento |
| `postgresql` | Runtime | Driver JDBC para PostgreSQL |
| `lombok` | — | Redução de boilerplate (getters, setters, constructors) |
| `spring-boot-starter-test` | 4.0.6 | Testes unitários e de integração |

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas com separação clara entre API, domínio e infraestrutura:

```
com.projetoEletro/
├── ProjetoEletroApplication.java    # Entry point Spring Boot
├── ServletInitializer.java            # Configuração para deploy WAR (opcional)
│
├── api/
│   ├── controller/                  # REST Controllers (expõem endpoints)
│   │   ├── AnuncioController.java
│   │   ├── CategoriaController.java
│   │   ├── ConteudoEducativoController.java
│   │   ├── DenunciaController.java
│   │   ├── GrupoController.java
│   │   ├── MensagemController.java
│   │   ├── PessoaController.java
│   │   ├── PontoColetaController.java
│   │   ├── Usuariocontroller.java
│   │   └── UsuarioGrupoController.java
│   │
│   ├── dto/
│   │   ├── post/                    # DTOs para criação (POST)
│   │   ├── put/                     # DTOs para atualização (PUT/PATCH)
│   │   └── response/                # DTOs de resposta (GET)
│   │
│   └── mapper/                      # Conversão Entity ↔ DTO
│
├── domain/
│   ├── exception/                   # Exceções customizadas
│   ├── model/                       # Entidades JPA (@Entity)
│   ├── repository/                  # Interfaces Spring Data JPA
│   └── service/                     # Regras de negócio + interfaces
│       └── *Impl.java               # Implementações dos services
│
└── infra/
    ├── AppConfig.java               # Bean PasswordEncoder (BCrypt)
    ├── SecurityConfig.java          # CORS aberto + CSRF desabilitado
    ├── WebSocketConfig.java         # Configuração de WebSocket
    ├── cors/                        # (extensível)
    └── openapi/                     # (extensível)
```

---

## 🗄️ Entidades e Relacionamentos

| Entidade | Descrição | Principais Campos |
|----------|-----------|-------------------|
| **Pessoa** | Dados pessoais desnormalizados do usuário | `id_pessoa`, `nome`, `cpf`, `data_nascimento`, `whatsapp` |
| **Usuario** | Credenciais e flags de autenticação | `id_usuario`, `email`, `senha` (hash), `foto`, `bloqueio_publicacao`, `bloqueio_chat` |
| **Grupo** | Perfis de acesso | `id_grupo`, `descricao` (ex: Administrador, Usuário Comum) |
| **UsuarioGrupo** | Vínculo N:N entre usuário e grupo | `id_usuario_grupo`, `id_usuario`, `id_grupo` |
| **Categoria** | Taxonomia dos anúncios | `id_categoria`, `slug` (único), `nome`, `icone` |
| **Anuncio** | Doações e trocas de eletrônicos | `id_anuncio`, `titulo`, `descricao`, `tipo`, `condicao`, `foto`, `status` (pendente/aprovado/rejeitado) |
| **Mensagem** | Chat entre usuários | `id_mensagem`, `texto`, `remetente_email`, `destinatario_email`, `id_anuncio` |
| **Denuncia** | Registro de denúncias | `id_denuncia`, `tipo`, `motivo`, `status` (aberta/em_analise/resolvida) |
| **ConteudoEducativo** | Textos/vídeos da home | `id_conteudo_educativo`, `titulo`, `categoria`, `texto`, `link_video`, `ativo` |
| **PontoColeta** | Locais de descarte em Manaus | `id_ponto_coleta`, `nome`, `latitude`, `longitude`, `endereco`, `horario` |

### Relacionamentos

```
Pessoa (1) ←→ (1) Usuario
Usuario (1) ←→ (N) UsuarioGrupo ←→ (N) Grupo
Usuario (1) ←→ (N) Anuncio
Categoria (1) ←→ (N) Anuncio (via slug)
Anuncio (1) ←→ (N) Mensagem
Usuario (1) ←→ (N) Denuncia
```

> Script SQL completo disponível em `db_eletro.sql` na pasta do frontend.

---

## 🌐 Controllers e Endpoints REST

### Usuários — `/usuarios`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/usuarios` | Lista todos |
| `POST` | `/usuarios/registrar` | Cadastra novo usuário |
| `POST` | `/usuarios/login` | Autentica por email ou CPF + senha |
| `GET` | `/usuarios/{id}` | Busca por ID |
| `GET` | `/usuarios/email/{email}` | Busca por email |
| `PUT` | `/usuarios/{id}` | Atualiza usuário |
| `PATCH` | `/usuarios/senha` | Altera senha |
| `DELETE` | `/usuarios/{id}` | Remove usuário |
| `GET` | `/usuarios/bloqueados` | Lista usuários punidos |
| `PATCH` | `/usuarios/{id}/punir` | Aplica bloqueio (body: `bloqueioPublicacao`, `bloqueioChat`) |
| `PATCH` | `/usuarios/{id}/punir/remover` | Revoga todas as punições |
| `DELETE` | `/usuarios/{id}/conta` | Exclui conta e anúncios em cascata |

### Anúncios — `/anuncios`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/anuncios` | Lista todos |
| `GET` | `/anuncios/aprovados` | Apenas anúncios aprovados (home) |
| `GET` | `/anuncios/pendentes` | Apenas pendentes (painel admin) |
| `POST` | `/anuncios` | Cria anúncio (status = pendente) |
| `GET` | `/anuncios/{id}` | Busca por ID |
| `GET` | `/anuncios/usuario/{usuarioId}` | Anúncios de um usuário |
| `GET` | `/anuncios/categoria/{categoriaId}` | Anúncios por categoria |
| `PUT` | `/anuncios/{id}` | Atualiza anúncio |
| `DELETE` | `/anuncios/{id}` | Remove anúncio |
| `PATCH` | `/anuncios/{id}/aprovar` | Aprova anúncio |
| `PATCH` | `/anuncios/{id}/rejeitar` | Rejeita anúncio |

### Mensagens — `/mensagens`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/mensagens` | Lista todas |
| `POST` | `/mensagens` | Envia mensagem |
| `GET` | `/mensagens/{id}` | Busca por ID |
| `PUT` | `/mensagens/{id}` | Atualiza mensagem |
| `DELETE` | `/mensagens/{id}` | Remove mensagem |
| `GET` | `/mensagens/anuncio/{anuncioId}` | Mensagens de um anúncio |
| `GET` | `/mensagens/email/{email}` | Mensagens de um usuário |
| `GET` | `/mensagens/conversa` | Histórico entre dois usuários (params: `emailA`, `emailB`, `anuncioId` opcional) |

### Denúncias — `/denuncias`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/denuncias` | Lista todas |
| `POST` | `/denuncias` | Cria denúncia |
| `GET` | `/denuncias/{id}` | Busca por ID |
| `GET` | `/denuncias/usuario/{usuarioId}` | Denúncias de um usuário |
| `PUT` | `/denuncias/{id}` | Atualiza denúncia |
| `DELETE` | `/denuncias/{id}` | Remove denúncia |
| `PATCH` | `/denuncias/{id}/resolver` | Marca como resolvida |

### Conteúdo Educativo — `/ConteudosEducativos`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/ConteudosEducativos` | Lista todos |
| `GET` | `/ConteudosEducativos/ativos` | Apenas ativos (home) |
| `POST` | `/ConteudosEducativos` | Cria conteúdo |
| `GET` | `/ConteudosEducativos/{id}` | Busca por ID |
| `PUT` | `/ConteudosEducativos/{id}` | Atualiza conteúdo |
| `DELETE` | `/ConteudosEducativos/{id}` | Remove conteúdo |

### Pontos de Coleta — `/pontosColetas`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/pontosColetas` | Lista todos |
| `POST` | `/pontosColetas` | Adiciona ponto |
| `GET` | `/pontosColetas/{id}` | Busca por ID |
| `PUT` | `/pontosColetas/{id}` | Atualiza ponto |
| `DELETE` | `/pontosColetas/{id}` | Remove ponto |

### Categorias — `/categorias`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/categorias` | Lista todas |
| `POST` | `/categorias` | Cria categoria |
| `GET` | `/categorias/{id}` | Busca por ID |
| `PUT` | `/categorias/{id}` | Atualiza categoria |
| `DELETE` | `/categorias/{id}` | Remove categoria |

### Grupos — `/grupos`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/grupos` | Lista todos |
| `POST` | `/grupos` | Cria grupo |
| `GET` | `/grupos/{id}` | Busca por ID |
| `PUT` | `/grupos/{id}` | Atualiza grupo |
| `DELETE` | `/grupos/{id}` | Remove grupo |

### Usuário-Grupo — `/usuariogrupos`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/usuariogrupos` | Lista todos |
| `POST` | `/usuariogrupos` | Vincula usuário a grupo |
| `GET` | `/usuariogrupos/{id}` | Busca por ID |
| `PUT` | `/usuariogrupos/{id}` | Atualiza vínculo |
| `DELETE` | `/usuariogrupos/{id}` | Remove vínculo |

---

## 📦 DTOs

Cada domínio possui três tipos de DTO:

- **PostDTO** (`api.dto.post.*`) — usado nos corpos das requisições `POST`. Contém `@Valid` e anotações de validação (`@NotBlank`, `@Size`, etc.).
- **PutDTO** (`api.dto.put.*`) — usado nos corpos das requisições `PUT`/`PATCH`.
- **ResponseDTO** (`api.dto.response.*`) — retornado em todas as respostas `GET` e nos corpos de criação/atualização.

**Mappers** (`api.mapper.*`) realizam a conversão bidirecional entre **Entity** e **DTOs**, evitando expor diretamente as entidades JPA na API.

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** (JDK)
- **Maven** (wrapper incluso: `mvnw` / `mvnw.cmd`)
- **PostgreSQL** (ou acesso a uma instância remota)

### Passo a passo

```bash
# Clone o repositório e entre na pasta
cd "c:\Users\lusiv\Desktop\projetoEletro - Copia"

# 1. Configure o banco em src/main/resources/application.properties
#    Ou use variáveis de ambiente:
#    DATASOURCE_URL=jdbc:postgresql://host:5432/postgres?sslmode=require
#    DATASOURCE_USERNAME=postgres
#    DATASOURCE_PASSWORD=sua-senha

# 2. Execute via Maven Wrapper (Windows)
.\mvnw spring-boot:run

# Ou compile e rode o JAR
.\mvnw clean package
java -jar target\projetoEletro-0.0.1-SNAPSHOT.jar
```

A aplicação iniciará na porta **8081** (ou a porta definida na variável `PORT`):

```
http://localhost:8081
```

### Script SQL de inicialização

O schema e a carga inicial de dados (grupos e categorias) estão disponíveis em:

```
../Site5 - Copia/db_eletro.sql
```

Execute este script no PostgreSQL antes do primeiro uso para garantir que:
- As tabelas existam (ou deixe `spring.jpa.hibernate.ddl-auto=update` criar automaticamente)
- Os grupos `Administrador` (id=1) e `Usuário Comum` (id=2) estejam inseridos
- As 11 categorias de eletrônicos estejam cadastradas

---

## ⚙️ Configuração do Banco

Arquivo: `src/main/resources/application.properties`

```properties
spring.application.name=projetoEletro
server.port=${PORT:8081}

# PostgreSQL
spring.datasource.url=${DATASOURCE_URL:jdbc:postgresql://host:5432/postgres?sslmode=require}
spring.datasource.username=${DATASOURCE_USERNAME:postgres}
spring.datasource.password=${DATASOURCE_PASSWORD:senha}
spring.datasource.driver-class-name=org.postgresql.Driver

# Upload de imagens
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
server.tomcat.max-http-form-post-size=10MB

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

> **Atenção**: `ddl-auto=update` é conveniente para desenvolvimento. Em produção, considere usar `validate` ou Flyway/Liquibase.

---

## 🔒 Segurança e CORS

A configuração atual (`SecurityConfig.java`) é aberta para facilitar o desenvolvimento com o frontend estático:

- **CSRF desabilitado** — necessário para APIs REST consumidas por SPA/frontend estático
- **CORS aberto** — permite qualquer origem (`*`) e todos os métodos HTTP (`GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`, `PATCH`)
- **Autorização** — todas as requisições são permitidas (`permitAll()`). O controle de acesso ao painel admin é feito no frontend verificando `is_admin` na sessão.

**Hash de senhas**: Todas as senhas são armazenadas com **BCrypt** via `PasswordEncoder`. Nunca em texto plano.

---

## 🧪 Testes

```bash
.\mvnw test
```

O projeto utiliza `spring-boot-starter-test` como base para testes unitários e de integração.

---

## 📝 Notas para Desenvolvedores

### Convenções de código
- **Pacotes**: `com.projetoEletro.{camada}.{subpacote}`
- **Controllers**: anotados com `@RestController` e `@RequestMapping("/recurso")`
- **Services**: interface (`*Service`) + implementação (`*ServiceImpl`)
- **Entities**: anotadas com `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne`, etc.
- **DTOs**: usam Lombok (`@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`) e Jakarta Validation
- **Mappers**: classes utilitárias com métodos estáticos de conversão

### Adicionar um novo domínio
1. Crie a **Entity** em `domain.model`
2. Crie a **Repository** interface em `domain.repository` (extends `JpaRepository`)
3. Crie a **Service** interface + **Impl** em `domain.service`
4. Crie os **DTOs** (post, put, response) em `api.dto.*`
5. Crie o **Mapper** em `api.mapper`
6. Crie o **Controller** em `api.controller`
7. Adicione o script DDL em `db_eletro.sql` (opcional se usar `ddl-auto=update`)

### Deploy no Railway
O projeto está configurado para deploy no Railway. Certifique-se de:
- Definir as variáveis de ambiente `DATASOURCE_URL`, `DATASOURCE_USERNAME`, `DATASOURCE_PASSWORD`
- A porta é automaticamente configurada pela variável `PORT` fornecida pelo Railway

---

## 📄 Licença

Projeto acadêmico (TCC). Uso educacional.

---

**Desenvolvido com 💚 para a comunidade de Manaus**

**Versão atualizada em Maio de 2026**
