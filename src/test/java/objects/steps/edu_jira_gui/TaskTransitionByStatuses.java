package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.CheckVisibilAndClick;
import objects.steps.edu_jira_gui.collective.InputIframe;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static util.Config.getConfigValue;

public class TaskTransitionByStatuses extends EdujiraIfellowRuSecureDashboard {

    @Step("Переводим созданную задачу по статусам")
    public static void taskTransitionByStatuses() {

        Selenide.open(getConfigValue("issueUrl") + CreateIssue.issueKey);
        getWebDriver().manage().window().maximize();
        assert $(By.tagName("body")).shouldBe(Condition.visible).exists() : "Body нет на странице.";

        CheckVisibilAndClick.checkVisibilAndClick(inWorkButton, "В работе");
        CheckVisibilAndClick.checkVisibilAndClick(closeButton, "closeButton");

        CheckVisibilAndClick.checkVisibilAndClick(businessProcessButton, "Бизнес процесс");
        CheckVisibilAndClick.checkVisibilAndClick(executedButton, "Исполнено");
        InputIframe.inputIframe("Комментарий", "Комментарий Задачи 'Исполнено'");
        CheckVisibilAndClick.checkVisibilAndClick(executedButtonForm, "Исполнено На форме Исполнено");
        CheckVisibilAndClick.checkVisibilAndClick(closeButton, "closeButton");

        CheckVisibilAndClick.checkVisibilAndClick(businessProcessButton, "Бизнес процесс");
        CheckVisibilAndClick.checkVisibilAndClick(confirmedButton, "Подтверждено");
        InputIframe.inputIframe("Комментарий", "Комментарий Задачи 'Подтверждено'");
        CheckVisibilAndClick.checkVisibilAndClick(confirmedButtonForm, "Подтверждено На форме Подтверждено");
        CheckVisibilAndClick.checkVisibilAndClick(closeButton, "closeButton");

        Assertions.assertEquals("Готово", issueStatus.getOwnText(), "Не верный статус задачи");
    }
}
