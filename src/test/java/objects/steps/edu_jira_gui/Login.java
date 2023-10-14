package objects.steps.edu_jira_gui;

import objects.elements.EdujiraIfellowRuLogin;

import static io.qameta.allure.Allure.step;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static objects.steps.edu_jira_gui.collective.InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData;

public class Login extends EdujiraIfellowRuLogin {

    public static void authorization(String login, String password) {

        step("Вводим login: " + login + " вводим password: " + password + " и нажимаем Войти", () -> {
            inputFieldEnterAndVerifyingData(inputLogin, login, "Имя пользователя", '0');
            inputFieldEnterAndVerifyingData(inputPassword, password, "Пароль", '0');
            buttonCheckVisibilityClick(battonLogin, "Войти");
        });

}
}
