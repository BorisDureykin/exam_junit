import hooks.WebHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.api_edu_jira.GoToProjectCountIssueApi.getCountIssuesInProjectApi;
import static objects.steps.api_edu_jira.GoToProjectCountIssueApi.getProjectKey;
import static objects.steps.gui_edu_jira.CreateIssue.createIssue;
import static objects.steps.gui_edu_jira.GoToProjectAntCountIssues.*;
import static objects.steps.gui_edu_jira.Login.authorization;
import static objects.steps.gui_edu_jira.Login.invalidAuthorization;
import static objects.steps.gui_edu_jira.OpenUrl.checkUrlAndTitlePage;
import static objects.steps.gui_edu_jira.OpenUrl.openUrl;
import static objects.steps.gui_edu_jira.ProfileIn.checkProfileIn;
import static objects.steps.gui_edu_jira.ProfileIn.profileIn;
import static objects.steps.gui_edu_jira.SearchIssue.*;
import static objects.steps.gui_edu_jira.TaskTransitionByStatuses.taskTransitionByStatuses;
import static util.Config.getConfigValue;


@Epic("GUI Test")
@Feature("ifellowEduJira.ru Tests")

public class GuiEduJiraTest extends WebHooks {

    private final String keyUrl = "UrlIfellowJira";
    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private final String pageTitle = "System Dashboard - Jira";
    private final String nameCoToProject = "TEST";
    private final String taskName = "TestSelenium";
    private final String affectedVersion = "Version 2.0";
    private final String issuesStatus = "Сделать";
    private String inputTopic = "Create Issue student AT14 GUI";

    @Test
    @Story("Open Url GUI")
    @DisplayName("Проверка доступности сайта")
    @Tag("GUI")
    @Tag("EduJira")
    public void testOpenUrl() {
        openUrl(keyUrl);
        checkUrlAndTitlePage(keyUrl, pageTitle);
    }

    @Test
    @Story("Authorization GUI")
    @DisplayName("Авторизация позитивный кейс")
    @Tag("GUI")
    @Tag("EduJira")
    public void testAuthorizationPositive() {
        openUrl(keyUrl);
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
        openUrl(keyUrl);
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
        openUrl(keyUrl);
        authorization(login, password);
        invalidAuthorization();
    }


    @Test
    @Story("Go To Project GUI")
    @DisplayName("Вход в проект TEST и проверка количества задач в проекте")
    @Tag("GUI")
    @Tag("EduJira")
    public void testGoToProject() {
        openUrl(keyUrl);
        authorization(login, password);
        goToProjectAntCountIssues(nameCoToProject);
        countIssues(nameCoToProject);

        String endpoint = "/rest/api/2/project/";
        String method = "GET";
        String statusCode = "200";
        String pathSchema = "ifellow_edu_jira/schemaGetProjectKey.json";
        getProjectKey(nameCoToProject, endpoint, method, statusCode, pathSchema);

        endpoint = "/rest/api/2/search";
        method = "GET";
        statusCode = "200";
        pathSchema = "ifellow_edu_jira/schemaSearch.json";
        getCountIssuesInProjectApi(endpoint, method, statusCode, pathSchema);

        comparingCountIssues(nameCoToProject);
    }

    @Test
    @Story("Task Search")
    @DisplayName("Поиск задачи и проверка статуса задачи и поля Затронуты версии")
    @Tag("GUI")
    @Tag("EduJira")
    public void testTaskSearch() {
        openUrl(keyUrl);
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
        openUrl(keyUrl);
        authorization(login, password);
        createIssue(inputTopic);
        taskTransitionByStatuses();
    }

    @Test
    @Story("Create Issue GUI")
    @DisplayName("Проверка появления предупреждения и невозможности создания задачи без заполнения поля 'Тема' ")
    @Tag("GUI")
    @Tag("EduJira")
    public void testCreateIssueInvalidTopic() {
        openUrl(keyUrl);
        authorization(login, password);
        inputTopic="";
        createIssue(inputTopic);
    }


}
