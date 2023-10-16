package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static objects.steps.edu_jira_api.BaseAuthorizationRequest.baseAuthorizationRequest;

public class CreateIssueApi {
    public static String issueIdApi;

    @Step("Создаем задачу в проекте")
    public static String createIssueApi(String endpoint, String method, String statusCode, String pathSchema) {

        try {
            RequestSpecification request =  baseAuthorizationRequest();

            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyCreateIssue.json")));

            Response response = ResponseAllTests.responseGet(request, body, endpoint, method, statusCode, pathSchema);

            String responseBody = response.getBody().asString();

            issueIdApi = new JSONObject(responseBody).getString("id");

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "Ошибка Создания body.";
    }
}
