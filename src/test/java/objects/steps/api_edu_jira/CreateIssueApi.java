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
import static objects.steps.api_edu_jira.BaseAuthorizationRequest.baseAuthorizationRequest;

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

            String message = "Создана задач с ID: " + issueIdApi ;

            saveMessage("ID задачи", message);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "Ошибка Создания body.";
    }
}
