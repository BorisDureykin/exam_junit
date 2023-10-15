package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick;
import objects.steps.edu_jira_gui.collective.InputIframe;

import static objects.steps.edu_jira_gui.collective.AssertionUtils.assertEqualUtil;

public class TaskTransitionByStatuses extends EdujiraIfellowRuSecureDashboard {

    @Step("Переводим созданную задачу по статусам")
    public static void taskTransitionByStatuses() {

        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(inWorkButton, "В работе");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(closeButton, "closeButton");

        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(businessProcessButton, "Бизнес процесс");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(executedButton, "Исполнено");
        InputIframe.inputIframe("Комментарий", "Комментарий Задачи 'Исполнено'");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(executedButtonForm, "Исполнено На форме Исполнено");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(closeButton, "closeButton");

        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(businessProcessButton, "Бизнес процесс");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(confirmedButton, "Подтверждено");
        InputIframe.inputIframe("Комментарий", "Комментарий Задачи 'Подтверждено'");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(confirmedButtonForm, "Подтверждено На форме Подтверждено");
        ButtonCheckVisibilityClick.buttonCheckVisibilityClick(closeButton, "closeButton");
        assertEqualUtil("Готово", issueStatus.getOwnText(), "Не верный статус задачи");
    }
}
