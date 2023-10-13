package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import objects.elements.EdujiraIfellowRuProjectsTestIssues;
import objects.elements.EdujiraIfellowRuSecureDashboard;
import objects.steps.edu_jira_gui.collective.CheckVisibilAndClick;

import static io.qameta.allure.Allure.step;
import static util.Config.getConfigValue;


public class GoToProjectAntCountIssues extends EdujiraIfellowRuSecureDashboard {

    public static void goToProjectAntCountIssues(String keyNameCoToProject) {

        String nameCoToProject = getConfigValue(keyNameCoToProject);
        step("Заходим в проект: " + nameCoToProject, () -> {
            CheckVisibilAndClick.checkVisibilAndClick(goToProjectButton, "Project Button");
            CheckVisibilAndClick.checkVisibilAndClick(goToProjectLink, "Project Link");
            CheckVisibilAndClick.checkVisibilAndClick(EdujiraIfellowRuProjectsTestIssues.allIssues, "Задачи");
        });
    }
    public static void countIssues(String keyNameCoToProject) {

        String nameCoToProject = getConfigValue(keyNameCoToProject);
        step("Выводим количество задач в проекте: " + nameCoToProject, () -> {
            assert EdujiraIfellowRuProjectsTestIssues.countIssues.is(Condition.visible) : "Количество задач не отображается.";
            String newCountIssues = EdujiraIfellowRuProjectsTestIssues.countIssues.getOwnText().replace("1 из ", "");
            assert !newCountIssues.isEmpty() : "Нет значения в количестве задач.";
        });
    }

}


