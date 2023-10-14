import hooks.WebHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.edu_jira_gui.CreateIssue.createIssue;
import static objects.steps.edu_jira_gui.GoToProjectAntCountIssues.countIssues;
import static objects.steps.edu_jira_gui.GoToProjectAntCountIssues.goToProjectAntCountIssues;
import static objects.steps.edu_jira_gui.Login.authorization;
import static objects.steps.edu_jira_gui.OpenUrl.checkUrlAndTitlePage;
import static objects.steps.edu_jira_gui.OpenUrl.openUrl;
import static objects.steps.edu_jira_gui.ProfileIn.checkProfileIn;
import static objects.steps.edu_jira_gui.ProfileIn.profileIn;
import static objects.steps.edu_jira_gui.SearchIssue.checkSearchIssue;
import static objects.steps.edu_jira_gui.SearchIssue.searchIssue;
import static objects.steps.edu_jira_gui.TaskTransitionByStatuses.taskTransitionByStatuses;
import static util.Config.getConfigValue;


@Epic(value = "GUI Test")
@Feature(value = "ifellowEduJira.ru Tests")
public class GuiEduJiraTest extends WebHooks {

    private final String url = getConfigValue("Url");

    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private final String pageTitle = getConfigValue("pageTitle");
    private final String nameCoToProject = getConfigValue("nameCoToProject");
    private final String taskName = getConfigValue("taskName");
    private final String affectedVersion = getConfigValue("affectedVersion");
    private final String inputTopic = getConfigValue("inputTopic");
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
    @DisplayName("Test Authorization")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestAuthorization() {
        openUrl(url);
        authorization(login, password);
        profileIn();
        checkProfileIn(login);
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
        checkSearchIssue(issuesStatus, affectedVersion);
    }

    @Test
    @Story("Create Issue")
    @DisplayName("Test Create Issue And Transition By Statuses")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestCreateIssueAndTransitionByStatuses() {
        openUrl(url);
        authorization(login, password);
        createIssue(inputTopic);
        taskTransitionByStatuses();
    }
}
