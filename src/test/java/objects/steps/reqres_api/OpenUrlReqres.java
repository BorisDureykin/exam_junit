package objects.steps.reqres_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;

public class OpenUrlReqres extends ResponseAllTests {

    @Step("open Url Reqres")
    public static void openUrlReqres(RequestSpecification request) {

        String endpoint = "/api/unknown";

        String pathSchema = "reqres/SchemaOpenUrl.json";

        responseGet(request, null, endpoint, "GET", "200", pathSchema);

    }

}

