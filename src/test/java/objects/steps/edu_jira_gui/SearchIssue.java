package objects.steps.edu_jira_gui;

import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.EnterAndVerifyingDataInputField;
import org.junit.jupiter.api.Assertions;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.steps.edu_jira_gui.collective.CheckVisibilAndClick.checkVisibilAndClick;
import static util.Config.getConfigValue;

public class SearchIssue extends EdujiraIfellowRuSecureDashboard {

    public static void searchIssue(String keyTaskName) {

        String taskName = getConfigValue(keyTaskName);
        step("Производим поиск задачи с именем: " + taskName, () -> {
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(searchInput, taskName, "Поиск", '1');
            checkVisibilAndClick(issueLink, "issueLink");
        });
    }

    public static void checkSearchIssue(String keyAffectedVersion) {
        String affectedVersion = getConfigValue(keyAffectedVersion);
        step("Сверяем статус задачи и привязку к заданной версии: " + affectedVersion, () -> {
            saveScreenshot("Step Screenshot");

            Assertions.assertEquals("Сделать", issueStatus.getOwnText(), "Не верный статус задачи");
            if (!issueVersions.getOwnText().contains(affectedVersion)) {
                throw new org.openqa.selenium.WebDriverException("Ошибка в привязке к версии");
            }
        });
    }

}
