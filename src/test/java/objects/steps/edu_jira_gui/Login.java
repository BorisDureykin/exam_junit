package objects.steps.edu_jira_gui;

import objects.elements.EdujiraIfellowRuLogin;
import objects.steps.edu_jira_gui.collective.CheckVisibilAndClick;
import objects.steps.edu_jira_gui.collective.EnterAndVerifyingDataInputField;

import static io.qameta.allure.Allure.step;
import static util.Config.getConfigValue;

public class Login extends EdujiraIfellowRuLogin {

    public static void authorization(String keyLogin, String keyPassword) {

        String login = getConfigValue(keyLogin);
        String password = getConfigValue(keyPassword);
        step("Вводим login: " + login + " вводим password: " + password + " и нажимаем Войти", () -> {
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(inputLogin, login, "Имя пользователя", '0');
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(inputPassword, password, "Пароль", '0');
            CheckVisibilAndClick.checkVisibilAndClick(battonLogin, "Войти");
        });

}
}
