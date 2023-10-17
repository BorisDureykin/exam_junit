package objects.steps.api_edu_jira;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import objects.steps.api_all_request_respone.ResponseAllTests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

            responseGet(request, body, endpoint, metod, statusCode, pathSchema);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
