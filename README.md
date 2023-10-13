# Экзаменационное задание 1 junit, maven, selenide, allure


## Файл src/main/java/util/Config.java
реализована конфигурация системы на использование файла src/test/resources/setup.properties

## Файл src/test/java/hooks/WebHooks.java
Содержит методы BeforeAll и AfterEach

## В пакете src/test/java/tests
находится файлы с описанием и запуск тестов

## В пакете src/test/java/objects/elements
содержаться файлы с Xpath по страницам сайта https://edujira.ifellow.ru/

## В пакете src/test/java/steps
находиться логика выполнения тестов и проверки

## В пакете src/test/java/steps/edu_jira_gui
находиться шаги по GUI тестам сайта https://edujira.ifellow.ru/

### В пакете src/test/java/steps/edu_jira_gui/collective
- общие методы по GUI тестам сайта https://edujira.ifellow.ru/

## В классе  src/test/java/tests.CucumberEduJiraGuiRunnerTest.java
находится описание опций для запуска и запуск GUI тестов сайта https://edujira.ifellow.ru/ через Cucumber.

## В папке src/test/resources/feature/ifellowEdujira.feature
находится описание тестов GUI для сайта https://edujira.ifellow.ru/ через Cucumber.

### Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем body и title.

#### Test Authorization
Авторизуемся и проверяем авторизацию.

#### Test Go To Project
Заходим в проект и определяем количество задач в проекте и возвращаем полученное значение.

#### Test Task Search
Поиск и переход в заданную задачу и проверка статуса и привязки.

#### Test Create Issue And Transition By Statuses
Создание задачи с типом "Ошибка" и перевод созданной задачи по статусам.

## В пакете src/test/java/steps/request_respone_api
находятся классы с определением RequestSpecification и получением Response

### в папке src/test/java/steps/rick_and_morty_api
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

### в папке src/test/java/steps/reqres_api
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


## В пакете src/test/java/steps/edu_jira_api
реализована логика шагов теста сайта https://edujira.ifellow.ru с использованием Api

### Реализованы тесты:

#### Test OpenUrl
Открываем URL и проверяем statusCode.

#### Test Authorization
Авторизуемся и проверяем авторизацию.

#### Test Go To Project
Заходим в проект и определяем количество задач в проекте и возвращаем полученное значение.

#### Test Create Issue And Transition By Statuses
Создание задачи с типом "Ошибка" и перевод созданной задачи по статусам.


## В пакете src/test/java/steps/cucumber_calculator
находится тестовый класс с необходимой логикой провеки Калькулятора через Cucumber.

## В классе  src/test/java/CucumberCalculatorRunnerTest.java
находится описание опций для запуска и запуск тестов Калькулятора через Cucumber.

## В файле src/test/resources/feature/calculator.feature
находится описание тестов для калькулятора через Cucumber.






