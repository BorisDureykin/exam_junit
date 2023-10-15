package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick;
import objects.steps.edu_jira_gui.collective.InputFieldEnterAndVerifyingData;
import objects.steps.edu_jira_gui.collective.InputIframe;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.CreateIssueForm.*;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.*;

public class CreateIssue extends EdujiraIfellowRuSecureDashboard {

    public static String issueKey;

    public static void createIssue(String inputTopic) {

        step("Создаем задачу с типом Ошибка и темой: " + inputTopic + " и получаем номер созданной задачи", () -> {
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(createBatton, "Create Button");
            assertTrueVisible(issueTypeSelect, "Не отображаестя селектор Тип Задачи.");
            issueTypeSelect.doubleClick();
            InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData(issueTypeSelect, "Ошибка", "Тип Задачи", '1');
            InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData(issueSummary, inputTopic, "Тема", '0');
            InputIframe.inputIframe("Описание", "Описание Задачи");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(versionSelector, "Исправить в версиях");
            prioritetSelector.doubleClick();
            InputFieldEnterAndVerifyingData.inputFieldEnterAndVerifyingData(prioritetSelector, "Highest", "Приоритет", '1');
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(markSlectorClik, "Метки");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(markSlector, "Предложения");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(markSlectorClik, "Метки");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(markSlector, "Предложения");
            InputIframe.inputIframe("Окружение", "Окружение Задачи");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(touchedVersionsSelector, "Затронуты версии");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(relatedTagsSlector, "Связанные задачи");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(issueSlectorClik, "Задача селектор");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(issueSlector, "Выбор 3 Задачи");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(assignMeButton, "Назначить меня");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(createIssueButton, "Create Issues Button");

            if (inputTopic.isEmpty()){
                assertTrueVisible(summaryDescriptionError, "Не отображаестя предупреждение.");
                assertTrueContains("Вы должны определить тему по запросу.", summaryDescriptionError.getOwnText(), "Сообщение не верно.");
                saveScreenshot("Проверка ошибки создания задачи и вывод сообщения об ошибке: 'Вы должны определить тему по запросу.'");

            }else {
                assertTrueVisible(returnIssueKey, "Не отображаестя сообщение об успешном создании задачи.");
                String newIssueKey = returnIssueKey.shouldBe(Condition.visible).getOwnText();
                String target = " - " + inputTopic;
                issueKey = newIssueKey.replace(target, "");
                assertNotNullUtil(issueKey, "Нет номера задачи.");
                saveScreenshot("Создана задача №: " + issueKey);
            }
        });
    }
}
