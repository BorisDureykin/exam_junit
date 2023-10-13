package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class GoToProject extends ResponseAllTests {

    @Step("Переходим в проект: \"{0}\" и получаем ключ продукта")
    public static String getProjectKey(String projectName, RequestSpecification request) {

        String endpoint = "/rest/api/2/project/" + projectName;

        String pathSchema = "ifellow_edu_jira/schemaGetProjectKey.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        String projectKey = new JSONObject(responseBody).optString("key", null);

        Assertions.assertNotNull(projectKey, "Не удалось получить ключ проекта.");

        return projectKey;
    }

    @Step("Переходим в проект: \"{0}\" и получаем количество задач в проекте")
    public static int getCountIssuesInProject(String projectKey, RequestSpecification request) {

        String endpoint = "/rest/api/2/search";

        String jqlQuery = "project=" + projectKey + " AND resolution = Unresolved";

        request
                .queryParam("fields", "id")
                .queryParam("maxResults", "0")
                .queryParam("jql", jqlQuery);

        String pathSchema = "ifellow_edu_jira/schemaSearch.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        int countIssue = new JSONObject(responseBody).getInt("total");

        Assertions.assertTrue(countIssue > 0, "Задачи в проекте не найдены.");

        return countIssue;
    }

}
