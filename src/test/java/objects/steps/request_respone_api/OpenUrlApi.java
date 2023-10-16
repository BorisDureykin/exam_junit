package objects.steps.request_respone_api;

import static io.qameta.allure.Allure.step;
import static objects.steps.request_respone_api.RequestSpecificationAllTests.requestSpecificationAllTests;
import static util.Config.getConfigValue;

public class OpenUrlApi {


    public static void openUrlApi(String keyUrl, String endpoint, String method, String statusCode, String pathSchema) {

        String url = getConfigValue(keyUrl);
        step("Открываем Url: " + url + ", и проверяем statusCode и pathSchema.",()->{
            ResponseAllTests.responseGet(requestSpecificationAllTests(url), null, endpoint, method, statusCode, pathSchema);
        });
    }
}
