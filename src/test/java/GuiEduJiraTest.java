import com.codeborne.selenide.junit5.ScreenShooterExtension;
import hooks.WebHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static objects.steps.edu_jira_gui.GoToProjectAntCountIssues.countIssues;
import static objects.steps.edu_jira_gui.GoToProjectAntCountIssues.goToProjectAntCountIssues;
import static objects.steps.edu_jira_gui.Login.authorization;
import static objects.steps.edu_jira_gui.Login.invalidAuthorization;
import static objects.steps.edu_jira_gui.OpenUrl.checkUrlAndTitlePage;
import static objects.steps.edu_jira_gui.OpenUrl.openUrl;
import static objects.steps.edu_jira_gui.ProfileIn.checkProfileIn;
import static objects.steps.edu_jira_gui.ProfileIn.profileIn;
import static objects.steps.edu_jira_gui.SearchIssue.*;
import static util.Config.getConfigValue;


@Epic("GUI Test")
@Feature("ifellowEduJira.ru Tests")
@ExtendWith({ScreenShooterExtension.class})

public class GuiEduJiraTest extends WebHooks {

    private final String url = getConfigValue("UrlIfellowJira");
    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private final String pageTitle = "System Dashboard - Jira";
    private final String nameCoToProject = "Test (TEST)";
    private final String taskName = "TestSelenium";
    private final String affectedVersion = "Version 2.0";
    private final String inputTopic = "Create Issue student AT14 GUI";
    private final String issuesStatus = "Сделать";

    @Test
    @Story("Open Url")
    @DisplayName("Test Open Url")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestOpenUrl() {
        openUrl(url);
        checkUrlAndTitlePage(url, pageTitle);
    }

    @Test
    @Story("Authorization")
    @DisplayName("Авторизация позитивный кейс")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestAuthorizationPositive() {
        openUrl(url);
        authorization(login, password);
        profileIn();
        checkProfileIn(login);

    }

    @Test
    @Story("Authorization")
    @DisplayName("Авторизация ненверный логин")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestAuthorizationInvalidLogin() {

        login = "AAAA";
        openUrl(url);
        authorization(login, password);
        invalidAuthorization();

    }
    @Test
    @Story("Authorization")
    @DisplayName("Авторизация ненверный пароль")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestAuthorizationInvalidPassword() {

        password = "qwerty";
        openUrl(url);
        authorization(login, password);
        invalidAuthorization();
    }


    @Test
    @Story("Go To Project")
    @DisplayName("Test Go To Project")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestGoToProject() {
        openUrl(url);
        authorization(login, password);
        goToProjectAntCountIssues(nameCoToProject);
        countIssues(nameCoToProject);
    }

    @Test
    @Story("Task Search")
    @DisplayName("Test Task Search")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestTaskSearch() {
        openUrl(url);
        authorization(login, password);
        profileIn();
        searchIssue(taskName);
        checkAffectedIssue(affectedVersion);
        checkStatusIssue(issuesStatus);
    }

//    @Test
//    @Story("Create Issue")
//    @DisplayName("Test Create Issue And Transition By Statuses")
//    @Tag("GUI")
//    @Tag("EduJira")
//    public void TestCreateIssueAndTransitionByStatuses() {
//        openUrl(url);
//        authorization(login, password);
//        createIssue(inputTopic);
//        taskTransitionByStatuses();
//    }
}
