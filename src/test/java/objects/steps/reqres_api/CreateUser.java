package objects.steps.reqres_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;

import static org.hamcrest.Matchers.equalTo;


public class CreateUser extends ResponseAllTests {

    @Step("Создание пользователя с именем: \"{1}\", и job: \"{2}\"")
    public static void createUser(RequestSpecification request, String nameValue, String jobValue) {

        String endpoint = "/api/users";

        String filePath = "src/test/resources/reqres/user.json";

        String body = UpdateJsonObject.updateJsonObject(filePath, nameValue, jobValue);

        String pathSchema = "reqres/SchemaCreateUserReqres.json";

        Response response = responseGet(request, body, endpoint, "POST", "201", pathSchema);

        response
                .then()
                .assertThat()
                .body("name", equalTo(nameValue))
                .body("job", equalTo(jobValue));

    }
}
