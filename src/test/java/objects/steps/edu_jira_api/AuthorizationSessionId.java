package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static util.Config.getConfigValue;

public class AuthorizationSessionId extends ResponseAllTests {

    @Step("Авторизация и получение sessionId")
    public static String authorizationSessionId(RequestSpecification request) {

        try {

            String endpoint = "/rest/auth/1/session";

            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyAutorization.json")));

            body = body.replace("login", getConfigValue("login"));

            body = body.replace("userPassword", getConfigValue("password"));

            String pathSchema = "ifellow_edu_jira/schemaAuth.json";

            Response response = responseGet(request, body, endpoint, "POST", "200", pathSchema);

            String responseBody = response.getBody().asString();

            return new JSONObject(responseBody).getJSONObject("session").getString("value");

        } catch (IOException e) {

            e.printStackTrace();
        }
       return "Ошибка Создания body.";
    }

}
