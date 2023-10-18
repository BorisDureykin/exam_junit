package objects.steps.api_reqres;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.api_all_request_respone.ResponseAllTests;

import static hooks.WebHooks.saveMessage;
import static objects.steps.api_all_request_respone.RequestSpecificationAllTests.requestSpecificationAllTests;
import static objects.steps.api_reqres.UpdateJsonObject.getJsonObjectToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Config.getConfigValue;


public class CreateUser extends ResponseAllTests {

    @Step("Создание пользователя с именем: \"{nameValue}\", и job: \"{jobValue}\"")
    public static void createUser(String keyUrl, String nameValue, String jobValue,  String endpoint, String method, String statusCode, String pathSchema) {

        String url = getConfigValue(keyUrl);

        RequestSpecification request = requestSpecificationAllTests(url);

        String body = getJsonObjectToString();

        Response response = responseGet(request, body, endpoint, method, statusCode, pathSchema);

        String message = "Проверяем Поле 'name' и Поле 'job' на соответствие ожидаемым значениям- 'name': " + nameValue + " 'job': " + jobValue;
        saveMessage("Проверяем поля ответа ", message);

        assertEquals(nameValue, response.path("name"), "Поле 'name' не соответствует ожидаемому значению");
        assertEquals(jobValue, response.path("job"), "Поле 'job' не соответствует ожидаемому значению");
    }
}
