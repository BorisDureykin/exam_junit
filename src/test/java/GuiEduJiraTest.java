import com.codeborne.selenide.junit5.ScreenShooterExtension;
import hooks.WebHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import objects.steps.edu_jira_api.GoToProjectCountIssue;
import objects.steps.edu_jira_gui.CreateIssue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static objects.steps.edu_jira_gui.GoToProjectAntCountIssues.*;
import static objects.steps.edu_jira_api.BaseAuthorizationRequest.baseAuthorizationRequest;
import static objects.steps.edu_jira_api.GoToProjectCountIssue.getCountIssuesInProjectApi;
import static objects.steps.edu_jira_gui.CreateIssue.createIssue;
import static objects.steps.edu_jira_gui.Login.authorization;
import static objects.steps.edu_jira_gui.Login.invalidAuthorization;
import static objects.steps.edu_jira_gui.OpenUrl.checkUrlAndTitlePage;
import static objects.steps.edu_jira_gui.OpenUrl.openUrl;
import static objects.steps.edu_jira_gui.ProfileIn.checkProfileIn;
import static objects.steps.edu_jira_gui.ProfileIn.profileIn;
import static objects.steps.edu_jira_gui.SearchIssue.*;
import static objects.steps.edu_jira_gui.TaskTransitionByStatuses.taskTransitionByStatuses;
import static objects.steps.request_respone_api.RequestSpecificationAllTests.requestSpecificationAllTests;
import static util.Config.getConfigValue;


@Epic("GUI Test")
@Feature("ifellowEduJira.ru Tests")
@ExtendWith({ScreenShooterExtension.class})

public class GuiEduJiraTest extends WebHooks {

    private String url = getConfigValue("UrlIfellowJira");
    private RequestSpecification request = requestSpecificationAllTests(url);
    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private final String pageTitle = "System Dashboard - Jira";
    private final String nameCoToProject = "TEST";
    private final String taskName = "TestSelenium";
    private final String affectedVersion = "Version 2.0";
    private String inputTopic = "Create Issue student AT14 GUI";
    private final String issuesStatus = "Сделать";

    @Test
    @Story("Open Url GUI")
    @DisplayName("Проверка доступности сайта")
    @Tag("GUI")
    @Tag("EduJira")
    public void testOpenUrl() {
        openUrl(url);
        checkUrlAndTitlePage(url, pageTitle);
    }

    @Test
    @Story("Authorization GUI")
    @DisplayName("Авторизация позитивный кейс")
    @Tag("GUI")
    @Tag("EduJira")
    public void testAuthorizationPositive() {
        openUrl(url);
        authorization(login, password);
        profileIn();
        checkProfileIn(login);

    }

    @Test
    @Story("Authorization GUI")
    @DisplayName("Авторизация ненверный логин")
    @Tag("GUI")
    @Tag("EduJira")
    public void testAuthorizationInvalidLogin() {

        login = "AAAA";
        openUrl(url);
        authorization(login, password);
        invalidAuthorization();

    }
    @Test
    @Story("Authorization GUI")
    @DisplayName("Авторизация ненверный пароль")
    @Tag("GUI")
    @Tag("EduJira")
    public void testAuthorizationInvalidPassword() {

        password = "qwerty";
        openUrl(url);
        authorization(login, password);
        invalidAuthorization();
    }


    @Test
    @Story("Go To Project GUI")
    @DisplayName("Вход в проект TEST и проверка количества задач в проекте")
    @Tag("GUI")
    @Tag("EduJira")
    public void testGoToProject() {
        openUrl(url);
        authorization(login, password);
        goToProjectAntCountIssues(nameCoToProject);
        String newCountIssues = countIssues(nameCoToProject);

        request = baseAuthorizationRequest(request);
        String projectKey = GoToProjectCountIssue.getProjectKey(nameCoToProject, request);
        String countIssues = getCountIssuesInProjectApi(projectKey, request);
        comparingCountIssues(nameCoToProject, newCountIssues,countIssues);
    }

    @Test
    @Story("Task Search")
    @DisplayName("Поиск задачи и проверка статуса задачи и поля Затронуты версии")
    @Tag("GUI")
    @Tag("EduJira")
    public void testTaskSearch() {
        openUrl(url);
        authorization(login, password);
        profileIn();
        searchIssue(taskName);
        checkStatusIssue(issuesStatus);
        checkAffectedIssue(affectedVersion);
    }

    @Test
    @Story("Create Issue GUI")
    @DisplayName("Создание задачи и перевод её по статусам")
    @Tag("GUI")
    @Tag("EduJira")
    public void testCreateIssueAndTransitionByStatuses() {
        openUrl(url);
        authorization(login, password);
        createIssue(inputTopic);
        url = getConfigValue("issueUrl") + CreateIssue.issueKey;
        openUrl(url);
        taskTransitionByStatuses();
    }

    @Test
    @Story("Create Issue GUI")
    @DisplayName("Проверка появления предупреждения и невозможности создания задачи без заполнения поля 'Тема' ")
    @Tag("GUI")
    @Tag("EduJira")
    public void testCreateIssueInvalidTopic() {
        openUrl(url);
        authorization(login, password);
        inputTopic="";
        createIssue(inputTopic);
    }


}
