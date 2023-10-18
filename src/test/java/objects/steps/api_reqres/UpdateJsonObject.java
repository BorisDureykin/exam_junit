package objects.steps.api_reqres;

import io.qameta.allure.Step;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hooks.WebHooks.saveMessage;

public class UpdateJsonObject {

    private static UpdateJsonObject instance;
    private JSONObject newUserJson;

    @Step("Создание body с новыми значениями name: \"{nameValue}\", job: \"{jobValue}\"")
    public static void updateJsonObject(String filePath, String nameValue, String jobValue) {
        instance = new UpdateJsonObject();
        try {
            String initialUserJson = new String(Files.readAllBytes(Paths.get(filePath)));

            instance.newUserJson = new JSONObject(initialUserJson);

            instance.newUserJson.put("name", nameValue);
            instance.newUserJson.put("job", jobValue);
            String message = "Изменен Json новое body: " + instance.newUserJson.toString();
            saveMessage("Изменение Json" ,message);

        } catch (IOException e) {
            e.printStackTrace();
            instance.newUserJson = new JSONObject();
        }
    }
    public static String getJsonObjectToString() {

        return instance.newUserJson.toString();
    }
}


