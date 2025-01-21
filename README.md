<div align="center">
    <img src="https://www.svgrepo.com/show/530128/movie.svg" alt="Logo" width="100" height="100">
<h1 align="center">PotyCine</h1>
  <p align="center">
    Uma solução para entusiastas do cinema independente.
  </p>
</div>

## Sobre o projeto
Plataforma digital que conecta produtores e cinéfilos, promovendo eventos de cinema local e independente. Produtores podem criar perfis, divulgar seus portfólios, cadastrar eventos e gerenciar exibições de filmes, enquanto cinéfilos têm acesso a ingressos, podem favoritar eventos e explorar uma programação personalizada. A aplicação busca fomentar a cultura cinematográfica, destacando produções independentes e festivais regionais.
## Ferramentas
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
## Build
### Pré-requisitos
**1. Java Development Kit (JDK)**
- Versão mínima: 23

**2. Maven**
- Versão: 3.9+

**3. Git**

**4. IDE (opcional)**
- Recomenda-se IntelliJ Idea que possui suporte para Spring.
### Etapas para Build
**1. Clone o repositório**
```
git clone URL_REPO
cd PotyCine
```

**2. Configurar acesso ao banco de dados**
- A configuração do acesso ao banco de dados 
localiza-se no arquivo "application.properties". 
Nesse arquivo deve-se configurar o banco de 
dados utilizado, o usuário e a senha.

**3. Compilar o projeto**
```
mvn compile
```

**5. Gerar o pacote**
- O arquivo será gerado no diretório "target/"
```
mvn package
```
### Executando o projeto
**1. Executar diretamente o JAR**
```
cd target
java -jar nome_do_arquivo.jar
```
**2. Executar via IDE**
- Abrir o projeto na IDE.
- Localizar a classe principal (PotyCineApplication) e executá-la.
## Permissões
* Admin `ADMINISTRATOR`
* Produtor `PRODUCER` -> Gerencia eventos
* Usuário `USER` -> Vê programação e comprar ingressos

## Rotas
### Registro/Login (`/users`)
#### Criar Usuário
`POST /users`

Permissão: Não necessita autenticar

Request:
```
{
    "name": "Fulano da Silva",
    "email": "fulano@mail.com",
    "password": "12345",
    "role": "PRODUCER"
}
```
* Role pode possuir os valores: `ADMINISTRATOR`, `USER`, `PRODUCER`
#### Fazer login
`POST /users/login`

Permissão: Não necessita autenticar

Request:
```
{
    "email": "fulano@mail.com",
    "password": "12345"
}
```
* Retorna _Bearer token_ que deve ser adicionado ao cabeçalho da requisição
#### Dados do usuário por id
`GET /users/{id}`

Permissão: Logado

Response:
```
{
    "id": 1,
    "name": "Usuário teste",
    "email": "user@mail.com",
    "role": "USER"
}
```
#### Tickets do usuário
`GET /users/{id}/tickets`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "user": {
            "id": 1,
            "name": "Admin",
            "email": "admin@mail.com",
            "role": "ADMINISTRATOR"
        },
        "event": {
            "id": 1,
            "name": "Festival de Cinema Independente",
            "description": "Mostra de curtas e longas metragens independentes.",
            "address": "Av. da Cultura, 45",
            "startDate": "2024-12-10T21:00:00.000+00:00",
            "endDate": "2024-12-16T01:00:00.000+00:00",
            "producer": {
                "id": 1,
                "user": {
                    "id": 2,
                    "name": "Produtor",
                    "email": "produtor@mail.com",
                    "role": "PRODUCER"
                },
                "companyName": "Film Company",
                "portfolio": {
                    "Youtube": "www.youtube.com",
                    "Instagram": "www.instagram.com"
                }
            }
        },
        "price": 20.0
    }
]
```

#### Favoritos do usuário
`GET /users/{id}/favorites`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "user": {
            "id": 3,
            "name": "Fulano da Silva",
            "email": "fulano3@mail.com",
            "role": "USER"
        },
        "event": {
            "id": 1,
            "name": "Festival de Cinema Independente",
            "description": "Mostra de curtas e longas metragens independentes.",
            "address": "Av. da Cultura, 45",
            "startDate": "2024-12-10T21:00:00.000+00:00",
            "endDate": "2024-12-16T01:00:00.000+00:00",
            "producer": {
                "id": 1,
                "user": {
                    "id": 2,
                    "name": "Fulano da Silva",
                    "email": "fulano2@mail.com",
                    "role": "PRODUCER"
                },
                "companyName": "Fox Films",
                "portfolio": {
                    "Youtube": "www.youtube.com"
                }
            }
        }
    }
]
```
### Produtor (`/producers`)
#### Adicionar dados do produtor
`POST /producers`

Permissão: Produtor

Request:
```
{
    "userId": 1,
    "companyName": "Film Company",
    "portfolio": {
        "Instagram": "www.instagram.com",
        "Youtube": "www.youtube.com"
    }
}
```
* `userId`: Id do usuário com role `PRODUCER`
#### Atualizar produtor
`PUT /producers/{id}`

Permissão: Produtor

Request:
```
{
    "userId": 1,
    "companyName": "Fox Films",
    "portfolio": {
        "Youtube": "www.youtube.com"
    }
}
```
#### Dados do produtor por id
`GET /producers/{id}`

Permissão: Logado

Response:
```
{
    "id": 1,
    "user": {
        "id": 1,
        "name": "Fulano da Silva",
        "email": "fulano@mail.com",
        "role": "PRODUCER"
    },
    "companyName": "Fox Films",
    "portfolio": {
        "Youtube": "www.youtube.com"
    }
}
```
### Eventos (`/events`)
#### Adicionar evento
`POST /events`

Permissão: Produtor

Request:
```
{
    "name": "Festival de Cinema Independente",
    "description": "Mostra de curtas e longas metragens independentes.",
    "address": "Av. da Cultura, 45",
    "startDate": "2024-12-10T18:00:00",
    "endDate": "2024-12-15T22:00:00",
    "producerId": 1
}
```
### Obtém todos os eventos independente de suas datas.
`GET /events/all`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "name": "Festival de Cinema Independente",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    },
    {
        "id": 2,
        "name": "Festival de Cinema Independente 2",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    }
]
```
#### Eventos que ainda estão ativos (data final não atingida)
`GET /events/until-today`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "name": "Festival de Cinema Independente",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    },
    {
        "id": 2,
        "name": "Festival de Cinema Independente 2",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    }
]
```
#### Eventos do produtor
`GET /events/producer/{idProdutor}`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "name": "Festival de Cinema Independente",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    },
    {
        "id": 2,
        "name": "Festival de Cinema Independente 2",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-25T21:00:00.000+00:00",
        "endDate": "2024-12-26T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    },
    {
        "id": 3,
        "name": "Festival de Cinema Independente 2",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-10T21:00:00.000+00:00",
        "endDate": "2024-12-16T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 5,
                "name": "Producer",
                "email": "producer@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    }
]
```
#### Evento por id
`GET /events/{idEvento}`

Permissão: Logado

Response:
```
{
    "id": 2,
    "name": "Festival de Cinema Independente 2",
    "description": "Mostra de curtas e longas metragens independentes.",
    "address": "Av. da Cultura, 45",
    "startDate": "2024-12-25T21:00:00.000+00:00",
    "endDate": "2024-12-26T01:00:00.000+00:00",
    "producer": {
        "id": 1,
        "user": {
            "id": 5,
            "name": "Producer",
            "email": "producer@mail.com",
            "role": "PRODUCER"
        },
        "companyName": "Fox Films",
        "portfolio": {
            "Youtube": "www.youtube.com"
        }
    }
}
```
### Exibições (`/exhibits`)
#### Criar exibição em evento
`POST /exhibits`

Permissão: Produtor

Request:
```
{
    "movieTitle": "O Grande Filme",
    "movieDescription": "Uma incrível jornada cinematográfica.",
    "movieDuration": 120,
    "movieDirector": "Diretor X",
    "movieScript": "Script Y",
    "eventId": 2,
    "time": "2024-12-20T18:00:00"
}
```
#### Listar exibições do evento
`GET /exhibits/event/{idEvento}`

Permissão: Logado

Response:
```
[
    {
        "id": 1,
        "time": "2024-12-20T18:00:00.000+00:00",
        "movie": {
            "id": 1,
            "title": "O Grande Filme",
            "description": "Uma incrível jornada cinematográfica.",
            "duration": 120,
            "director": "Diretor X",
            "script": "Script Y"
        },
        "event": {
            "id": 1,
            "name": "Festival de Cinema Independente",
            "description": "Mostra de curtas e longas metragens independentes.",
            "address": "Av. da Cultura, 45",
            "startDate": "2024-12-25T21:00:00.000+00:00",
            "endDate": "2024-12-26T01:00:00.000+00:00",
            "producer": {
                "id": 1,
                "user": {
                    "id": 5,
                    "name": "Producer",
                    "email": "producer@mail.com",
                    "role": "PRODUCER"
                },
                "companyName": "Fox Films",
                "portfolio": {
                    "Youtube": "www.youtube.com"
                }
            }
        }
    },
    {
        "id": 2,
        "time": "2024-12-20T18:00:00.000+00:00",
        "movie": {
            "id": 2,
            "title": "O Grande Filme 2",
            "description": "Uma incrível jornada cinematográfica 2.",
            "duration": 120,
            "director": "Diretor X",
            "script": "Script Y"
        },
        "event": {
            "id": 1,
            "name": "Festival de Cinema Independente",
            "description": "Mostra de curtas e longas metragens independentes.",
            "address": "Av. da Cultura, 45",
            "startDate": "2024-12-25T21:00:00.000+00:00",
            "endDate": "2024-12-26T01:00:00.000+00:00",
            "producer": {
                "id": 1,
                "user": {
                    "id": 5,
                    "name": "Producer",
                    "email": "producer@mail.com",
                    "role": "PRODUCER"
                },
                "companyName": "Fox Films",
                "portfolio": {
                    "Youtube": "www.youtube.com"
                }
            }
        }
    }
]
```

### Tickets (`/tickets`)
#### Registra um ticket comprado pelo usuário.
`POST /tickets`

Permissão: Logado

Request: 

```
{
    "userId": 1,
    "eventId": 1,
    "price": 20
}
```

### Favoritos (`/favorites`)
#### Adiciona um evento como favorito pelo usuário.
`POST /favorites`

Permissão: Logado

Request:

```
{
    "userId": 1,
    "eventId": 1,
}
```

#### Remove um evento que o usuário favoritou anteriormente.
`DELETE /favorites/{idEvento}`

Permissão: Logado

Response:

```
{
    "id": 1,
    "user": {
        "id": 3,
        "name": "Fulano da Silva",
        "email": "fulano3@mail.com",
        "role": "USER"
    },
    "event": {
        "id": 1,
        "name": "Festival de Cinema Independente",
        "description": "Mostra de curtas e longas metragens independentes.",
        "address": "Av. da Cultura, 45",
        "startDate": "2024-12-10T21:00:00.000+00:00",
        "endDate": "2024-12-16T01:00:00.000+00:00",
        "producer": {
            "id": 1,
            "user": {
                "id": 2,
                "name": "Fulano da Silva",
                "email": "fulano2@mail.com",
                "role": "PRODUCER"
            },
            "companyName": "Fox Films",
            "portfolio": {
                "Youtube": "www.youtube.com"
            }
        }
    }
}
```