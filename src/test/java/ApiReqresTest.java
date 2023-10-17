import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import objects.steps.request_respone_api.RequestSpecificationAllTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.reqres_api.CreateUser.createUser;
import static objects.steps.reqres_api.UpdateJsonObject.createAndUpdate;
import static objects.steps.request_respone_api.OpenUrlApi.openUrlApi;

@Epic(value = "Api Test")
@Feature(value = "Reqres.in Tests")
@Story(value = "Api Reqres")
public class ApiReqresTest extends RequestSpecificationAllTests {
    private final String keyUrl = "UrlReqresIn";
    private final String filePath = "src/test/resources/reqres/user.json";
    private final String nameValue = "Tomato";
    private final String jobValue = "Eat maket";
    private String endpoint;
    private String method;
    private String statusCode;
    private String pathSchema;
    @Test
    @DisplayName("Проверка доступности сайта Reqres.in")
    @Tag("Api")
    @Tag("Reqres")
    public void testOpenUrl() {

        endpoint = "/api/unknown";

        method = "GET";

        statusCode="200";

        pathSchema = "reqres/SchemaOpenUrl.json";

        openUrlApi(keyUrl, endpoint, method, statusCode, pathSchema);

    }

    @Test
    @DisplayName("Создание пользователя")
    @Tag("Api")
    @Tag("Reqres")
    public void testCreate() {

        createAndUpdate(filePath, nameValue, jobValue);

        endpoint = "/api/users";

        method = "POST";

        statusCode="201";

        pathSchema = "reqres/SchemaCreateUserReqres.json";

        createUser( keyUrl, nameValue, jobValue, endpoint, method, statusCode, pathSchema );
    }
}
