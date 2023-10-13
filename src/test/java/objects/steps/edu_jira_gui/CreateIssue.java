package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.CheckVisibilAndClick;
import objects.steps.edu_jira_gui.collective.EnterAndVerifyingDataInputField;
import objects.steps.edu_jira_gui.collective.InputIframe;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.CreateIssueForm.*;
import static util.Config.getConfigValue;

public class CreateIssue extends EdujiraIfellowRuSecureDashboard {

    public static String issueKey;

    public static void createIssue(String keyInputTopic) {

        String inputTopic = getConfigValue(keyInputTopic);
        step("Создаем задачу с типом Ошибка и темой: " + inputTopic + " и получаем номер созданной задачи", () -> {
            CheckVisibilAndClick.checkVisibilAndClick(createBatton, "Create Button");
            issueTypeSelect.shouldBe(Condition.visible).doubleClick();
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(issueTypeSelect, "Ошибка", "Тип Задачи", '1');
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(issueSummary, inputTopic, "Тема", '0');
            InputIframe.inputIframe("Описание", "Описание Задачи");
            CheckVisibilAndClick.checkVisibilAndClick(versionSelector, "Исправить в версиях");
            prioritetSelector.doubleClick();
            EnterAndVerifyingDataInputField.enterAndVerifyingDataInputField(prioritetSelector, "Highest", "Приоритет", '1');
            CheckVisibilAndClick.checkVisibilAndClick(markSlectorClik, "Метки");
            CheckVisibilAndClick.checkVisibilAndClick(markSlector, "Предложения");
            CheckVisibilAndClick.checkVisibilAndClick(markSlectorClik, "Метки");
            CheckVisibilAndClick.checkVisibilAndClick(markSlector, "Предложения");
            InputIframe.inputIframe("Окружение", "Окружение Задачи");
            CheckVisibilAndClick.checkVisibilAndClick(touchedVersionsSelector, "Затронуты версии");
            CheckVisibilAndClick.checkVisibilAndClick(relatedTagsSlector, "Связанные задачи");
            CheckVisibilAndClick.checkVisibilAndClick(issueSlectorClik, "Задача селектор");
            CheckVisibilAndClick.checkVisibilAndClick(issueSlector, "Выбор 3 Задачи");
            CheckVisibilAndClick.checkVisibilAndClick(assignMeButton, "Назначить меня");

            CheckVisibilAndClick.checkVisibilAndClick(createIssueButton, "Create Issues Button");

            String newIssueKey = returnIssueKey.shouldBe(Condition.visible).getOwnText();
            saveScreenshot("Step Screenshot");

            assert returnIssueKey.is(Condition.visible) : "Задача не создана";
            String target = " - " + inputTopic;
            issueKey = newIssueKey.replace(target, "");
            assert !issueKey.isEmpty() : "Нет номера задачи.";
        });
    }
}
