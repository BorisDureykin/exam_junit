# Экзаменационное задание 1 junit, maven, selenide, allure
# Добавил тесты с Rest Assured + junit + allure

### Запуск тестов через терминал: mvn clean test


## Файл src/main/java/util/Config.java
- Реализована конфигурация системы на использование файла src/test/resources/application.properties

## Файл src/test/java/hooks/WebHooks.java
- Содержит методы BeforeAll и AfterEach Attachment лдля создания скриншота и записи сообщения
- BeforeAll и AfterEach вызываются только при выполнении GUI тестов

# GUI тесты сайта https://edujira.ifellow.ru/

## В пакете src/test/java/objects/elements
- Содержаться файлы с Xpath по страницам сайта https://edujira.ifellow.ru/

## В пакете src/test/java/objects/steps/gui_edu_jira
- Находиться логика выполнения тестов и проверки по GUI тестам сайта https://edujira.ifellow.ru/

### src/test/java/objects/steps/gui_edu_jira/for_all
- Общие методы по GUI тестам сайта https://edujira.ifellow.ru/

## Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем body и title.

#### Test Authorization
- Авторизуемся и проверяем соответствие имени полязователя
- Позитивная авторизация с верными логин и пароль
- 2 проверки с неверными логин или паролем

#### Test Go To Project
- Заходим в проект и определяем количество задач в проекте отбражаемое на экране и возвращаем полученное значение.
- Отправляем API запрос  и определяем количество задач в проекте 
- Сверяем полученные значения.

#### Test Task Search
Поиск и переход в заданную задачу и проверка статуса и привязки.

#### Test Create Issue And Transition By Statuses
- Создание задачи с типом "Ошибка" и перевод созданной задачи по статусам.
- Проверка не возможности создания задачи без заполнения поля "Тема"

## В пакете src/test/java/objects/steps/api_all_request_respone
находятся классы с определением RequestSpecification и получением Response по API тестам

# API тесты сайта https://edujira.ifellow.ru/

## В пакете src/test/java/objects/steps/api_edu_jira
реализована логика шагов теста сайта https://edujira.ifellow.ru с использованием Api

### Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем statusCode.

#### Test Authorization
- Авторизуемся и проверяем наличие sessionID.
- Позитивная авторизация с верными логин и пароль
- 2 проверки с неверными логин или паролем

#### Test Go To Project
Заходим в проект и определяем количество задач в проекте и возвращаем полученное значение.

#### Test Create Issue And Transition By Statuses
Создание задачи с типом "Ошибка" и перевод созданной задачи по статусам.


# API тесты сайта https://rickandmortyapi.com/api

### в папке src/test/java/objects/steps/api_rick_and_morty
реализована логика шагов теста сайта https://rickandmortyapi.com/api

### Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем statusCode и body на соответствие схеме.

#### Test testGetCharacter
Находим информацию по персонажу Морти Смит.
Выбираем из ответа последний эпизод, где появлялся Морти.
Получаем из списка последнего эпизода последнего персонажа.
Получаем данные по местонахождению и расе этого персонажа.
Проверяем, этот персонаж той же расы и находится там же где и Морти?

# API тесты сайта https://reqres.in/

### в папке src/test/java/objects/steps/api_reqres
реализована логика шагов теста по сату https://reqres.in/

### Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем statusCode и body на соответствие схеме.

#### Test testCreate

Создан тест запрос для создания пользователя
Body в запрос предается из ранее созданного файла, меняется
name и добавляется поле Job
{ "name": "Tomato", "job": "Eat maket" }
Проверяем, что получили ответ 201.
Сверяем, что полученный response имеет валидные данные по значениям key и value.

