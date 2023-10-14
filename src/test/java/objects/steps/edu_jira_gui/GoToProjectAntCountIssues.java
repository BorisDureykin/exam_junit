package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import objects.elements.EdujiraIfellowRuProjectsTestIssues;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.ButtonCheckVisibilityClick;

import static io.qameta.allure.Allure.step;
import static util.Config.getConfigValue;


public class GoToProjectAntCountIssues extends EdujiraIfellowRuSecureDashboard {

    public static void goToProjectAntCountIssues(String nameCoToProject) {

        step("Заходим в проект: " + nameCoToProject, () -> {
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectButton, "Project Button");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(goToProjectLink, "Project Link");
            ButtonCheckVisibilityClick.buttonCheckVisibilityClick(EdujiraIfellowRuProjectsTestIssues.allIssues, "Задачи");
        });
    }
    public static void countIssues(String nameCoToProject) {

        step("Выводим количество задач в проекте: " + nameCoToProject, () -> {
            assert EdujiraIfellowRuProjectsTestIssues.countIssues.is(Condition.visible) : "Количество задач не отображается.";
            String newCountIssues = EdujiraIfellowRuProjectsTestIssues.countIssues.getOwnText().replace("1 из ", "");
            assert !newCountIssues.isEmpty() : "Нет значения в количестве задач.";
        });
    }

}


