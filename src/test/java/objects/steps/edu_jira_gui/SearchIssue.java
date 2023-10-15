package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;

import static hooks.WebHooks.saveScreenshot;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertEqualUtils;
import static objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static objects.steps.edu_jira_gui.collective.InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchIssue extends EdujiraIfellowRuSecureDashboard {

    @Step("Производим поиск задачи с именем: {taskName}")
    public static void searchIssue(String taskName) {

        inputFieldEnterAndVerifyingData(searchInput, taskName, "Поиск", '1');
        buttonCheckVisibilityClick(issueLink, "issueLink");
    }

    @Step("Сверяем поле 'затронуты версии': {affectedVersion}")
    public static void checkAffectedIssue(String affectedVersion) {

        String actualVersion = issueVersions.getOwnText();
        assertEqualUtils(affectedVersion, actualVersion, "Ошибка заполнения поля 'затронуты версии'");
        saveScreenshot("Сверяем поле 'затронуты версии': " + affectedVersion);
    }


    @Step("Сверяем статус задачи: {issuesStatus}")
    public static void checkStatusIssue(String issuesStatus) {

        String actualStatus = issueStatus.getOwnText();
        System.out.println(actualStatus + issuesStatus);
        assertEqualUtils(issuesStatus, actualStatus, "Не верный статус задачи");
        saveScreenshot("Сверяем статус задачи: "+issuesStatus);
    }
}
