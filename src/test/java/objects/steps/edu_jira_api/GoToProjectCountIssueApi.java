package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;

import static java.lang.Integer.valueOf;
import static objects.steps.edu_jira_api.BaseAuthorizationRequest.baseAuthorizationRequest;
import static objects.steps.request_respone_api.RequestSpecificationAllTests.requestSpecificationAllTests;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.Config.getConfigValue;

public class GoToProjectCountIssueApi extends ResponseAllTests {

    private static RequestSpecification request = requestSpecificationAllTests(getConfigValue("UrlIfellowJira"));

    public static String countIssueApi;

    @Step("Переходим в проект: \"{0}\" и получаем ключ продукта")
    public static void getProjectKey(String projectName) {

        request = baseAuthorizationRequest(request);

        String endpoint = "/rest/api/2/project/" + projectName;

        String pathSchema = "ifellow_edu_jira/schemaGetProjectKey.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        String projectKey = new JSONObject(responseBody).optString("key", null);

        assertNotNull(projectKey, "Не удалось получить ключ проекта.");

    }

    @Step("Переходим в проект: \"{0}\" и получаем количество задач в проекте")
    public static void getCountIssuesInProjectApi(String projectKey) {

        request = baseAuthorizationRequest(request);

        String endpoint = "/rest/api/2/search";

        String jqlQuery = "project=" + projectKey + " AND resolution = Unresolved";

        request
                .queryParam("fields", "id")
                .queryParam("maxResults", "0")
                .queryParam("jql", jqlQuery);

        String pathSchema = "ifellow_edu_jira/schemaSearch.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        countIssueApi = String.valueOf(valueOf(new JSONObject(responseBody).getInt("total")));

        assertNotNull(countIssueApi,  "Нет значения в количестве задач.");

    }

}
