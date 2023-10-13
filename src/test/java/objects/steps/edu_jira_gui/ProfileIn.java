package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import org.junit.jupiter.api.Assertions;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.steps.edu_jira_gui.collective.CheckVisibilAndClick.checkVisibilAndClick;
import static util.Config.getConfigValue;

public class ProfileIn extends EdujiraIfellowRuSecureDashboard {

    @Step("Заходим в профиль")
    public static void profileIn() {
        checkVisibilAndClick(profileBatton, "profileButton");
        checkVisibilAndClick(profileLink, "profileLink");
    }

    public static void checkProfileIn(String keyLogin) {

        String login = getConfigValue(keyLogin);
        step("Сверяем имя профиля с: " + login , () -> {
            saveScreenshot("Step Screenshot");

            Assertions.assertEquals(nameUser.shouldBe(Condition.visible).getOwnText(), login, "Не авторизованы");
        });
    }
}
