import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import objects.steps.reqres_api.CreateUser;
import objects.steps.reqres_api.OpenUrlReqres;
import objects.steps.request_respone_api.RequestSpecificationAllTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static util.Config.getConfigValue;

@Epic(value = "Api Test")
@Feature(value = "Reqres.in Tests")
@Story(value = "Api Reqres")
public class ApiReqresTest extends RequestSpecificationAllTests {

    public RequestSpecification request = requestSpecificationAllTests(getConfigValue("UrlReqresIn"));

    public String nameValue = "Tomato";
    public String jobValue = "Eat maket";

    @Test
    @DisplayName("Проверка доступности сайта Reqres.in")
    @Tag("Api")
    @Tag("Reqres")
    public void testOpenUrl() {

        OpenUrlReqres.openUrlReqres(request);
    }

    @Test
    @DisplayName("Создание пользователя")
    @Tag("Api")
    @Tag("Reqres")
    public void testCreate() {

        CreateUser.createUser(request, nameValue, jobValue);
    }
}
