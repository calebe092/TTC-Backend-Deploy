# CRUD Categoria e Anúncio - Documentação

## Estrutura Criada

### 1. Repositórios
- **CategoriaRepository**: Interface JpaRepository para Categoria
- **AnuncioRepository**: Interface JpaRepository para Anúncio com métodos customizados

### 2. DTOs

#### Categoria
- **CategoriaPostDTO**: Para criação de nova categoria
  - slug (obrigatório, único)
  - nome (obrigatório)
  - icone

- **CategoriaPutDTO**: Para atualização de categoria
  - nome (obrigatório)
  - icone

- **CategoriaResponseDTO**: Resposta da API com todos os dados

#### Anúncio
- **AnuncioPostDTO**: Para criação de novo anúncio
  - titulo (obrigatório)
  - descricao
  - tipo
  - condicao
  - marca
  - foto
  - bairro
  - status
  - usuarioId (obrigatório)
  - categoriaId

- **AnuncioPutDTO**: Para atualização de anúncio
  - titulo (obrigatório)
  - descricao
  - tipo
  - condicao
  - marca
  - foto
  - bairro
  - status
  - categoriaId

- **AnuncioResponseDTO**: Resposta da API com todos os dados

### 3. Mappers
- **CategoriaMapper**: Converte entre Categoria e DTOs
- **AnuncioMapper**: Converte entre Anuncio e DTOs

### 4. Services

#### CategoriaService (Interface)
- listarCategorias()
- salvarCategoria(CategoriaPostDTO)
- buscarCategoriaPorId(Long)
- buscarCategoriaPorSlug(String)
- atualizarCategoria(Long, CategoriaPutDTO)
- deletarCategoria(Long)

#### CategoriaServiceImpl
Implementação completa com validações

#### AnuncioService (Interface)
- listarAnuncios()
- salvarAnuncio(AnuncioPostDTO)
- buscarAnuncioPorId(Long)
- buscarAnunciosPorUsuario(Long)
- buscarAnunciosPorCategoria(Long)
- atualizarAnuncio(Long, AnuncioPutDTO)
- deletarAnuncio(Long)

#### AnuncioServiceImpl
Implementação completa com validações

### 5. Controllers

#### CategoriaController
Base: `/categorias`
- GET / - Lista todas as categorias
- POST / - Cria nova categoria
- GET /{id} - Busca categoria por ID
- GET /slug/{slug} - Busca categoria por slug
- PUT /{id} - Atualiza categoria
- DELETE /{id} - Deleta categoria

#### AnuncioController
Base: `/anuncios`
- GET / - Lista todos os anúncios
- POST / - Cria novo anúncio
- GET /{id} - Busca anúncio por ID
- GET /usuario/{usuarioId} - Lista anúncios de um usuário
- GET /categoria/{categoriaId} - Lista anúncios de uma categoria
- PUT /{id} - Atualiza anúncio
- DELETE /{id} - Deleta anúncio

## Exemplos de Uso

### Criar Categoria
```bash
POST http://localhost:8080/categorias
Content-Type: application/json

{
  "slug": "eletronicos",
  "nome": "Eletrônicos",
  "icone": "📱"
}
```

### Criar Anúncio
```bash
POST http://localhost:8080/anuncios
Content-Type: application/json

{
  "titulo": "iPhone 13 - Praticamente novo",
  "descricao": "iPhone 13 em perfeito estado, apenas 2 meses de uso",
  "tipo": "Venda",
  "condicao": "Novo",
  "marca": "Apple",
  "foto": "https://example.com/image.jpg",
  "bairro": "Centro",
  "status": "ATIVO",
  "usuarioId": 1,
  "categoriaId": 1
}
```

### Buscar Anúncios por Categoria
```bash
GET http://localhost:8080/anuncios/categoria/1
```

### Buscar Anúncios por Usuário
```bash
GET http://localhost:8080/anuncios/usuario/1
```

### Atualizar Anúncio
```bash
PUT http://localhost:8080/anuncios/1
Content-Type: application/json

{
  "titulo": "iPhone 13 - Praticamente novo - VENDIDO",
  "descricao": "iPhone 13 em perfeito estado",
  "tipo": "Venda",
  "condicao": "Novo",
  "marca": "Apple",
  "foto": "https://example.com/image.jpg",
  "bairro": "Centro",
  "status": "VENDIDO",
  "categoriaId": 1
}
```

### Deletar Anúncio
```bash
DELETE http://localhost:8080/anuncios/1
```

## Validações Implementadas

- Verificação de nulidade em todos os IDs
- Validação de required fields nos DTOs
- Tratamento de entidades não encontradas
- Validação de relacionamentos (usuário e categoria devem existir)
- Slug único na tabela de categorias

## Relacionamentos

- **Anúncio** possui ManyToOne para **Usuario**
- **Anúncio** possui ManyToOne para **Categoria**
- **Categoria** é independente

## Status Codes HTTP

- **201 Created**: Criação bem-sucedida
- **200 OK**: Sucesso em GET e PUT
- **204 No Content**: Deleção bem-sucedida
- **400 Bad Request**: Erro de validação
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro do servidor
