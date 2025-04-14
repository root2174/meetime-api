# Meetime API

API de integração com o HubSpot para gerenciamento de contatos.

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/downloads)
- Configure sua conta de desenvolvedor do Hubspot 
- Crie um aplicativo usando sua conta de desenvolvedor do hubspot
    - O aplicativa precisa ter as seguintes permissões obrigatórias:
        - oauth
        - crm.objects.contacts.read
        - crm.objects.contacts.write 
- Configure a URLs de redirecionamento na seção de Autenticação para: localhost:8080/api/v1/hubspot/auth/callback
- Copie as seguintes informações do aplicativo criado no hubspot: Client ID e Client Secret
## Configuração do Ambiente

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/meetime-api.git
cd meetime-api
```

2. Crie o arquivo `.env` na raiz do projeto com as seguintes variáveis (ou use o comando export no terminal):
```env
HUBSPOT_CLIENT_SECRET=seu_client_secret
HUBSPOT_CLIENT_ID=seu_client_id
```

3. Rodar projeto usando docker-compose:

```bash
docker-compose up -d
```

## Requests
```http request
GET http://localhost:8080/api/v1/hubspot/auth/url

###
POST http://localhost:8080/api/v1/hubspot/contacts
Content-Type: application/json

{
"authorization": "Bearer ${token}", // O authorization está no body e não como header pois esse é o authorization do Hubspot, e não da api construida aqui.
"properties": {
    "email": "lucas.m@lumon.com",
    "firstname": "Lucas",
    "lastname": "M."
  }
}
```

## Documentações utilizadas:

[Hubspot - Working with OAuth](https://developers.hubspot.com/docs/guides/apps/authentication/working-with-oauth)
[Hubspot - Contacts API](https://developers.hubspot.com/docs/reference/api/crm/objects/contacts#search)
[Hubspot - Webhooks](https://developers.hubspot.com/docs/guides/api/app-management/webhooks#webhook-subscriptions)
[Hubspot - Validating Webhooks](https://developers.hubspot.com/docs/guides/apps/authentication/validating-requests)

## Libs utilizadas.

- **Resilience4j**: biblioteca para implementação de padrões de resiliência como circuit breaker, rate limiting e retry. Utilizada para controlar o rate limit da API do HubSpot (100 requisições a cada 10 segundos e máximo de 250.000 requisições diárias).

- **Lombok**: biblioteca para reduzir código boilerplate em classes Java, gerando automaticamente getters, setters, construtores e outros métodos comuns através de anotações.

- **OpenFeign**: cliente HTTP declarativo para facilitar a comunicação com APIs REST. Utilizado para criar um cliente HTTP para a API do HubSpot de forma simples e declarativa.

- **Feign Form**: extensão do OpenFeign para suportar envio de formulários HTML. Utilizada para enviar requisições de autenticação OAuth2 para o HubSpot no formato application/x-www-form-urlencoded.

## Melhorias futuras
- Gravar o token de acesso para cada usuário em um banco de dados.
- Implementar estratégia de refresh token do hubspot.
- Implementar testes unitários e de integração.
- Monitoria e observabilidade como logs estruturados, adicionar tracing distribuido, dashboards para métricas...
- Implementar retry com backoff exponencial
- Cache para tokens de acesso