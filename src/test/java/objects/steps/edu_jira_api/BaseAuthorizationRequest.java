package objects.steps.edu_jira_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static util.Config.getConfigValue;

public class BaseAuthorizationRequest {

    @Step("Создаем RequestSpecification с Basic Authorization")
    public static RequestSpecification baseAuthorizationRequest(RequestSpecification request) {

        String loginPasword = getConfigValue("login") + ":" + getConfigValue("password");

        byte[] encodeLoginPassword = Base64.getEncoder().encode(loginPasword.getBytes());

        String byteLoginPassword = new String(encodeLoginPassword);

        return request
                .header("Authorization", "Basic " + byteLoginPassword);

    }

}
