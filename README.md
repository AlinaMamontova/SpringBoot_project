# Банковское приложение
## Структура проекта
Пакет src/main/java/com/example/springboot_project включает в себя 
9 пакетов:
### auth
1. `AuthenticationRequest` - класс запроса на аутентификацию

2. `RegisterRequest` - класс для запроса на регистрацию

3. `AuthenticationResponce` - класс для ответа на запрос аутентификации

4. `AuthenticationService` - класс сервиса по аутентификации и регистрации
   
### config
1. `ApplicationConfig` - класс для получения UserDetails, AuthenticationProvider, AuthenticationManager и PasswordEncoder
2. `JwtAuthenticationFilter` - проверка токена
3. `JwtService` - генерация токена
4. `SecurityConfiguration` - добавление созданного фильтра в SecurityFilterChain

### controller
1. **AccountController**<br/>
`GET /accounts` - получение всех счетов<br/>
`GET /accounts/{id}` - получение счета по id<br/>
`POST /accounts` - создание счета<br/>
`PUT /accounts/{id}` - изменение счета<br/>
`DELETE /accounts/{id}` - удаление счета<br/>
2. **AdminController**<br/>
`POST /admin/users` - создание пользователя с ролью ROLE_USER<br/>
`PUT /admin/users` - изменение пользователя<br/>
`DELETE /admin/users/{id}` - удаление пользователя по id<br/>
3. **AuthenticationController**<br/>
`POST /auth/sign-up` - регистрация пользователя<br/>
`POST /auth/sign-in` - аутентификация пользователя<br/>
4. **BankController**<br/>
`GET /banks` - получение банкa<br/>
`GET /banks/{id}` - получение банка по id<br/>
`POST /banks` - создание банка<br/>
`PUT /banks/{id}` - изменение банка<br/>
`DELETE /banks/{id}` - удаление банка<br/>
5. **CardController**<br/>
`GET /cards` - получение всех карт<br/>
`GET /cards/{id}` - получение карты по id<br/>
`POST /cards` - создание карты<br/>
`PUT /cards/{id}` - изменение карты<br/>
`DELETE /cards/{id}` - удаление карты<br/>
6. **CardTypeController**<br/>
`GET /cardTypes` - получение всех типов карт<br/>
`GET /cardTypes/{id}` - получение типа карты по id<br/>
`POST /cardTypes` - создание типа карты<br/>
`PUT /cardTypes/{id}` - изменение типа карты<br/>
`DELETE /cardTypes/{id}` - удаление типа карты<br/>
7. **ClientController**<br/>
`GET /clients` - получение всех клиентов карт<br/>
`GET /clients/{id}` - получение клиента по id<br/>
`POST /clients` - создание клиента<br/>
`PUT /clients/{id}` - изменение клиента<br/>
`DELETE /clients/{id}` - удаление клиента<br/>
8. **CurrencyController**<br/>
`GET /currencies` - получение валюты карт<br/>
`GET /currencies/{id}` - получение валюты по id<br/>
`POST /currencies` - создание валюты<br/>
`PUT /currencies/{id}` - изменение валюты<br/>
`DELETE /currencies/{id}` - удаление валюты<br/>
9. **DocumentController**<br/>
`GET /documents` - получение документа<br/>
`GET /documents/{id}` - получение документа по id<br/>
`POST /documents` - создание документа<br/>
`PUT /documents/{id}` - изменение документа<br/>
`DELETE /documents/{id}` - удаление документа<br/>
10. **DocumentTypeController**<br/>
`GET /documentTypes` - получение всех типов документов<br/>
`GET /documentTypes/{id}` - получение типа документа по id<br/>
`POST /documentTypes` - создание типа документа<br/>
`PUT /documentTypes/{id}` - изменение типа документа<br/>
`DELETE /documentTypes/{id}` - удаление типа документа<br/>

### dao
Слой взаимодействия с БД
### dto
Сущности для передачи данных в Presentation layer
### entity
Pojo-классы, которые отображают информацию определенной таблицы БД
### exception
Обработка исключений
### mapper
Преобразование entity в DTO и обратно
### service
Классы, содержащие бизнес-логу приложения