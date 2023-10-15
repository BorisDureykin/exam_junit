package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;

import static hooks.WebHooks.saveScreenshot;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertEqualUtil;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertTrueVisible;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;

public class ProfileIn extends EdujiraIfellowRuSecureDashboard {

    @Step("Заходим в профиль")
    public static void profileIn() {

        buttonCheckVisibilityClick(profileBatton, "profileButton");
        buttonCheckVisibilityClick(profileLink, "profileLink");
    }

    @Step("Сверяем имя профиля, ожидаемое значение:  {login}")
    public static void checkProfileIn(String login) {

        assertTrueVisible(nameUser, "Не отображается имя пользователя");
        assertEqualUtil(nameUser.getOwnText(), login, "Неверное имя пользователя, ожидаемое значение: "+login);
        saveScreenshot("Сверяем имя пользователя, ожидаемое значение:  " + login);
    }
}
