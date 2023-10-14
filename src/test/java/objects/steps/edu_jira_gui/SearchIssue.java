package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;

import static hooks.WebHooks.saveScreenshot;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static objects.steps.edu_jira_gui.collective.InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchIssue extends EdujiraIfellowRuSecureDashboard {

    @Step("Производим поиск задачи с именем: {taskName}")
    public static void searchIssue(String taskName) {

        inputFieldEnterAndVerifyingData(searchInput, taskName, "Поиск", '1');
        buttonCheckVisibilityClick(issueLink, "issueLink");
    }

    @Step("Сверяем статус задачи и привязку к заданной версии: {affectedVersion}")
    public static void checkSearchIssue(String issuesStatus, String affectedVersion) {

        assertEquals(issuesStatus, issueStatus.getOwnText(), "Не верный статус задачи");
        assertEquals(affectedVersion, issueVersions.getOwnText(), "Ошибка в привязке версии");
        saveScreenshot("Step Screenshot");
    }
}
