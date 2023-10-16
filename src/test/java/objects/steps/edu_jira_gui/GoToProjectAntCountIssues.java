package objects.steps.edu_jira_gui;

import io.qameta.allure.Step;
import objects.elements.EdujiraIfellowRuProjectsTestIssues;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick;

import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.EdujiraIfellowRuProjectsTestIssues.countIssues;
import static objects.steps.edu_jira_api.GoToProjectCountIssueApi.countIssueApi;
import static objects.steps.edu_jira_gui.collective.AssertionUtils.*;


public class GoToProjectAntCountIssues extends EdujiraIfellowRuSecureDashboard {

    private static String newCountIssuesGui;


    @Step("Заходим в проект: {nameCoToProject}")
    public static void goToProjectAntCountIssues(String nameCoToProject) {

        step("Заходим в проект: " + nameCoToProject, () -> {
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectButton, "Project Button");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectLink, "Project Link");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(EdujiraIfellowRuProjectsTestIssues.allIssues, "Задачи");
        });
    }

    @Step("Получение количества задач в проекте:  {nameCoToProject}")
    public static void countIssues(String nameCoToProject) {

        assertTrueVisible(countIssues, "Количество задач не отображается.");
        newCountIssuesGui = countIssues.getOwnText().replace("1 из ", "");
        assertNotNullUtil(newCountIssuesGui,  "Нет значения в количестве задач.");
        saveScreenshot("Получение количества задач в проекте: " + nameCoToProject);
    }

    @Step("Сравнение количества задач в проекте:  {nameCoToProject} ожидаемое значение:  {countIssues}")
    public static void comparingCountIssues(String nameCoToProject) {

        assertEqualUtil(countIssueApi, newCountIssuesGui, "Количество задач в проекте: "+ nameCoToProject +"Составляет: "+ countIssueApi+", Отображается : "+ newCountIssuesGui);
    }
}


