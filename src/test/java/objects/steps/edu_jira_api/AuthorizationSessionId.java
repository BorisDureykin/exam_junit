package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class AuthorizationSessionId extends ResponseAllTests {

    @Step("Авторизация")
    public static void authorizationSessionId(RequestSpecification request
            , String login
            , String password
            , String endpoint
            , String statusCode) {

        String pathSchema = null;
        if(Objects.equals(statusCode, "200")){
            pathSchema = "ifellow_edu_jira/schemaAuth.json";
        }

        try {


            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyAutorization.json")));

            body = body.replace("login", login);

            body = body.replace("userPassword", password);

            responseGet(request, body, endpoint, "POST", statusCode, pathSchema);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
