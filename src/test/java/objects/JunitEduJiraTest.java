package objects;

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


@Epic(value = "GUI Test")
@Feature(value = "ifellowEduJira.ru Tests")
@Story(value = "GUI EduJira")
public class JunitEduJiraTest extends WebHooks {
    @Test
    @DisplayName("Test Open Url")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestOpenUrl() {
        openUrl("Url");
        checkUrlAndTitlePage("Url", "pageTitle");
    }

    @Test
    @DisplayName("Test Authorization")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestAuthorization() {
        openUrl("Url");
        authorization("login", "password");
        profileIn();
        checkProfileIn("login");
    }

    @Test
    @DisplayName("Test Go To Project")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestGoToProject() {
        openUrl("Url");
        authorization("login", "password");
        goToProjectAntCountIssues("nameCoToProject");
        countIssues("nameCoToProject");
    }

    @Test
    @DisplayName("Test Task Search")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestTaskSearch() {
        openUrl("Url");
        authorization("login", "password");
        profileIn();
        searchIssue("taskName");
        checkSearchIssue("affectedVersion");
    }

    @Test
    @DisplayName("Test Create Issue And Transition By Statuses")
    @Tag("GUI")
    @Tag("EduJira")
    public void TestCreateIssueAndTransitionByStatuses() {
        openUrl("Url");
        authorization("login", "password");
        createIssue("inputTopic");
        taskTransitionByStatuses();
    }
}
