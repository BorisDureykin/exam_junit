package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TransitionByStatusesIssueApi {

    @Step("Перевод задачи Id: \"{1}\" по статусам")
    public static void transitionByStatuses(RequestSpecification request, String issueId) {

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/statuses.json")));

            JSONArray statusesArray = new JSONArray(jsonString);

            for (int i = 0; i < statusesArray.length(); i++) {

                JSONObject statusObject = statusesArray.getJSONObject(i);

                String transitionStatusId = statusObject.getString("transitionStatusId");

                String transitionStatusName = statusObject.getString("transitionStatusName");

                transitionByStatusesIssueApi(request, issueId, transitionStatusId, transitionStatusName);

                getStatusTransitionIssueApi(request, issueId, transitionStatusName);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @Step("Перевод задачи Id: \"{1}\" в статус \"{3}\"")
    public static void transitionByStatusesIssueApi(RequestSpecification request, String issueId, String transitionStatusId, String transitionStatusName) throws IOException {

            String endpoint = "/rest/api/2/issue/" + issueId + "/transitions";

            String body = new String(Files.readAllBytes(Paths.get("src/test/resources/ifellow_edu_jira/bodyTransition.json")));

            body = body.replace("transitionStatusId", transitionStatusId);

            ResponseAllTests.responseGet(request, body, endpoint, "POST", "204", null);

    }

    @Step("Проверка перевода задачи Id: \"{1}\" в статус \"{2}\"")
    public static void getStatusTransitionIssueApi(RequestSpecification request, String issueId, String transitionStatusName) {

        String endpoint = "/rest/api/2/issue/" + issueId;

        String pathSchema = "ifellow_edu_jira/schemaGetStatusTransitionIssue.json";

        Response response = ResponseAllTests.responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String gettingStatusName = new JSONObject(response.getBody().asString()).optJSONObject("fields").optJSONObject("status").optString("name");

        Assertions.assertEquals(transitionStatusName, gettingStatusName);

    }
}
