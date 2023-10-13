package objects.steps.request_respone_api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseAllTests {

    @Step("Выполняем запрос с параметрами: bodyValue = \"{bodyValue}\",endpoint = \"{endpoint}\", Метод = \"{method}\". Cверяем Response по statusCode = \"{statusCode}\", и схемой = \"{pathSchema}\"")
    public static Response responseGet(RequestSpecification request, String bodyValue, String endpoint, String method, String statusCode, String pathSchema) {

        RequestSpecification modifiedRequest = request;

        if (bodyValue != null) {
            modifiedRequest = modifiedRequest.body(bodyValue);
        }
        if (endpoint != null) {
            modifiedRequest = modifiedRequest.basePath(endpoint);
        }

        Response response;
        switch (method.toUpperCase()) {
            case "GET":
                response = modifiedRequest.get();
                break;
            case "POST":
                response = modifiedRequest.post();
                break;
            case "PUT":
                response = modifiedRequest.put();
                break;
            case "DELETE":
                response = modifiedRequest.delete();
                break;
            default:
                throw new IllegalArgumentException("HTTP method задан не верно: " + method);
        }

        assert response != null;

        response
                .then()
                .assertThat()
                .statusCode(Integer.parseInt(statusCode));

        if (pathSchema != null) {
            response
                    .then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(pathSchema));
        }

        Allure.addAttachment("API Response", "application/json", response.asString());
        return response;
    }
}

