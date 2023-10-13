package objects.steps.rick_and_morty_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;

public class OpenUrlRickAndMorty extends ResponseAllTests {

    @Step("Open Url Rick And Morty Api")
    public static void openUrlRickAndMortyApi(RequestSpecification request) {

        String pathSchema = "rick_and_morti/schemaOpenUrl.json";

        responseGet(request, null, null, "GET", "200", pathSchema);

    }

}
