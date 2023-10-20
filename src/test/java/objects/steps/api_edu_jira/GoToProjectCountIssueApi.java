package objects.steps.api_edu_jira;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.api_all_request_respone.ResponseAllTests;
import org.json.JSONObject;

import static hooks.WebHooks.saveMessage;
import static io.qameta.allure.Allure.step;
import static java.lang.Integer.valueOf;
import static objects.steps.api_edu_jira.BaseAuthorizationRequest.baseAuthorizationRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GoToProjectCountIssueApi extends ResponseAllTests {

    public static String countIssueApi;

    public static String projectKey;

    @Step("Переходим в проект: \"{projectName}\" и получаем ключ продукта")
    public static void getProjectKey(String projectName, String endpoint, String method, String statusCode, String pathSchema) {
        RequestSpecification request = baseAuthorizationRequest();
        endpoint = endpoint + projectName;

        Response response = responseGet(request, null, endpoint, method, statusCode, pathSchema);

        String responseBody = response.getBody().asString();

        projectKey = new JSONObject(responseBody).optString("key", null);

        assertNotNull(projectKey, "Не удалось получить ключ проекта.");
    }

    public static void getCountIssuesInProjectApi(String endpoint, String method, String statusCode, String pathSchema) {
        step("Переходим в проект: \"" + projectKey + "\" и получаем количество задач в проекте", () -> {
            String jqlQuery = "project=" + projectKey + " AND resolution = Unresolved";

            RequestSpecification request = baseAuthorizationRequest();

            request
                    .queryParam("fields", "id")
                    .queryParam("maxResults", "0")
                    .queryParam("jql", jqlQuery);

            Response response = responseGet(request, null, endpoint, method, statusCode, pathSchema);

            String responseBody = response.getBody().asString();

            countIssueApi = String.valueOf(valueOf(new JSONObject(responseBody).getInt("total")));

            String message = "Количество задач в проекте: " + projectKey + " Составляет: " + countIssueApi;

            saveMessage("Количество задач в проекте: " + projectKey, message);

            assertNotNull(countIssueApi, "Нет значения в количестве задач.");
        });
    }
}
