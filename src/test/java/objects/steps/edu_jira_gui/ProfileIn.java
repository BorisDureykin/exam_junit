package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import org.junit.jupiter.api.Assertions;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileIn extends EdujiraIfellowRuSecureDashboard {

    @Step("Заходим в профиль")
    public static void profileIn() {

        buttonCheckVisibilityClick(profileBatton, "profileButton");
        buttonCheckVisibilityClick(profileLink, "profileLink");
    }

    public static void checkProfileIn(String login) {

        step("Сверяем имя профиля с: " + login , () -> {

            assertEquals(nameUser.shouldBe(Condition.visible).getOwnText(), login, "Не авторизованы");
            saveScreenshot("Сверяем имя профиля с: " + login);
        });
    }
}
