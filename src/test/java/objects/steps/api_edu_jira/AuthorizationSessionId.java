package objects.steps.api_edu_jira;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.api_all_request_respone.ResponseAllTests;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hooks.WebHooks.saveMessage;
import static objects.steps.api_all_request_respone.RequestSpecificationAllTests.requestSpecificationAllTests;
import static util.Config.getConfigValue;

public class AuthorizationSessionId extends ResponseAllTests {

    @Step("Авторизация")
    public static void authorizationSessionId(String keyUrl, String login, String password, String endpoint, String metod, String statusCode, String pathSchema) {


        try {
            RequestSpecification request = requestSpecificationAllTests(getConfigValue(keyUrl));

            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyAutorization.json")));

            body = body.replace("login", login);

            body = body.replace("userPassword", password);

            Response response = responseGet(request, body, endpoint, metod, statusCode, pathSchema);

            int statusCode1 = response.getStatusCode();

            if(statusCode1 == 200){
                String responseBody = response.getBody().asString();

                JSONObject jsonObject = new JSONObject(responseBody);

                String sesionId = jsonObject.getJSONObject("session").getString("value");

                String message = "Успешная авторизация sesionId: " + sesionId ;

                saveMessage("Успешная авторизация", message);
            } else {

                String message = "Авторизация не удалась не верные логин или пароль";

                saveMessage("Не авторизованы", message);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
