package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;

public class OpenUrl {


    @Step("Открываем Url")
    public static void openUrlApi(RequestSpecification request) {

        String endpoint = "/rest/api/2/resolution";

        String pathSchema = "ifellow_edu_jira/schemaOpenUrl.json";

        ResponseAllTests.responseGet(request, null, endpoint, "GET", "200", pathSchema);

    }
}
