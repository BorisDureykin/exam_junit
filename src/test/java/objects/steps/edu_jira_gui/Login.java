package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuLogin;

import static hooks.WebHooks.saveScreenshot;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertTrueContains;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertTrueVisible;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static objects.steps.edu_jira_gui.collective.InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData;

public class Login extends EdujiraIfellowRuLogin {

    @Step("Вводим login: {login} вводим password: {password} и нажимаем Войти")
    public static void authorization(String login, String password) {

        inputFieldEnterAndVerifyingData(inputLogin, login, "Имя пользователя", '0');
        inputFieldEnterAndVerifyingData(inputPassword, password, "Пароль", '0');
        buttonCheckVisibilityClick(battonLogin, "Войти");
    }

    @Step("Проверка ошибки авторизации и вывод сообщения об ошибке: 'Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз.'")
    public static void invalidAuthorization() {

        assertTrueVisible(userNameError, "Не отображаестя предупреждение.");
        assertTrueContains(userNameError.getOwnText(), "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз.", "Сообщение не верно.");
        saveScreenshot("Проверка ошибки авторизации и вывод сообщения об ошибке: 'Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз.'");
    }
}
