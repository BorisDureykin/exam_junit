package objects.steps.reqres_api;

import io.qameta.allure.Step;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateJsonObject {

    @Step("Создание body с новыми значениями name: \"{nameValue}\", job: \"{jobValue}\"")
    public static String updateJsonObject(String filePath, String nameValue, String jobValue) {

        try {
            String initialBody = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject body = new JSONObject(initialBody);

            body.put("name", nameValue);

            body.put("job", jobValue);

            return body.toString();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "Ошибка Создания body.";
    }

}
