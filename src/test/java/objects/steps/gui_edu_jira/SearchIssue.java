package objects.steps.gui_edu_jira;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;

import static hooks.WebHooks.saveScreenshot;
import static objects.steps.gui_edu_jira.for_all.AssertionUtils.assertEqualUtil;
import static objects.steps.gui_edu_jira.for_all.AssertionUtils.assertTrueVisible;
import static objects.steps.gui_edu_jira.for_all.ButtonCheckVisibilityClick.buttonCheckVisibilityClick;
import static objects.steps.gui_edu_jira.for_all.InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData;

public class SearchIssue extends EdujiraIfellowRuSecureDashboard {

    @Step("Производим поиск задачи с именем: {taskName}")
    public static void searchIssue(String taskName) {

        inputFieldEnterAndVerifyingData(searchInput, taskName, "Поиск", '1');

        buttonCheckVisibilityClick(issueLink, "issueLink");
    }

    @Step("Сверяем поле 'затронуты версии', ожидаемое значение:  {affectedVersion}")
    public static void checkAffectedIssue(String affectedVersion) {

        assertTrueVisible(issueVersions, "Не отображается  поле 'затронуты версии'");

        String actualVersion = issueVersions.getOwnText();

        assertEqualUtil(affectedVersion, actualVersion, "Ошибка заполнения поля 'затронуты версии'");

        saveScreenshot("Сверяем поле 'затронуты версии', ожидаемое значение: " + affectedVersion);
    }


    @Step("Сверяем статус задачи, ожидаемое значение: {issuesStatus}")
    public static void checkStatusIssue(String issuesStatus) {

        assertTrueVisible(issueStatus, "Не отображается  поле 'статус задачи'");

        String actualStatus = issueStatus.getOwnText();

        assertEqualUtil(issuesStatus, actualStatus, "Не верный статус задачи");

        saveScreenshot("Сверяем статус задачи, ожидаемое значение: " + issuesStatus);
    }
}
