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

    private static Response response;
    private static String nameValue;
    private static String jobValue;

    @Step("Создание пользователя с именем: \"{nameValue}\", и job: \"{jobValue}\"")
    public static void createUser(String keyUrl, String inNameValue, String inJobValue, String endpoint, String method, String statusCode, String pathSchema) {

        String url = getConfigValue(keyUrl);

        nameValue = inNameValue;

        jobValue = inJobValue;

        RequestSpecification request = requestSpecificationAllTests(url);

        String body = getJsonObjectToString();

        response = responseGet(request, body, endpoint, method, statusCode, pathSchema);
    }

    @Step("Сверяем ответ с ожидаемым значением полей name и job")
    public static void checkNameAndJob() {

        String actualName = response.path("name");

        String actualjob = response.path("job");

        String message = "Проверяем Поле 'name' на соответствие. Ожидаемое значение: " + nameValue + ", актуальное значение: " + actualName;

        saveMessage("Проверяем поле name", message);

        assertEquals(nameValue, actualName, "Поле 'name' не соответствует ожидаемому значению");

        message = "Проверяем Поле 'job' на соответствие. Ожидаемое значение: " + jobValue + ", актуальное значение: " + actualjob;

        saveMessage("Проверяем поле job ", message);

        assertEquals(jobValue, actualjob, "Поле 'job' не соответствует ожидаемому значению");
    }
}
