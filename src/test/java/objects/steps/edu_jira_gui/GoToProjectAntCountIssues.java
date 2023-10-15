package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuProjectsTestIssues;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.EdujiraIfellowRuProjectsTestIssues.countIssues;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.*;


public class GoToProjectAntCountIssues extends EdujiraIfellowRuSecureDashboard {

    public static void goToProjectAntCountIssues(String nameCoToProject) {

        step("Заходим в проект: " + nameCoToProject, () -> {
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectButton, "Project Button");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectLink, "Project Link");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(EdujiraIfellowRuProjectsTestIssues.allIssues, "Задачи");
        });
    }

    @Step("Получение количества задач в проекте:  {nameCoToProject}")
    public static String countIssues(String nameCoToProject) {

        assertTrueVisible(countIssues, "Количество задач не отображается.");
        String newCountIssues = countIssues.getOwnText().replace("1 из ", "");
        assertNotNullUtil(newCountIssues,  "Нет значения в количестве задач.");
        saveScreenshot("Получение количества задач в проекте: " + nameCoToProject);
        return newCountIssues;
    }

    @Step("Получение количества задач в проекте:  {nameCoToProject}")
    public static void comparingCountIssues(String nameCoToProject, String newCountIssues, String countIssues) {

        assertEqualUtil(countIssues, newCountIssues, "Количество задач в проекте: "+ nameCoToProject +"Составляет: "+ countIssues+", Отображается : "+ newCountIssues );
    }
}


