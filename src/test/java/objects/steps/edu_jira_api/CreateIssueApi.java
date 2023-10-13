package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateIssueApi {

    @Step("Создаем задачу в проекте")
    public static String createIssueApi(RequestSpecification request) {

        try {
            String endpoint = "/rest/api/2/issue";

            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyCreateIssue.json")));

            String pathSchema = "ifellow_edu_jira/schemaCreateIssue.json";

            Response response = ResponseAllTests.responseGet(request, body, endpoint, "POST", "201", pathSchema);

            String responseBody = response.getBody().asString();

            return new JSONObject(responseBody).getString("id");

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "Ошибка Создания body.";
    }
}
