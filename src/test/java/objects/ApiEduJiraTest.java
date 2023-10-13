package objects;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import objects.steps.edu_jira_api.AuthorizationSessionId;
import objects.steps.edu_jira_api.GoToProject;
import objects.steps.edu_jira_api.OpenUrl;
import objects.steps.request_respone_api.RequestSpecificationAllTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.edu_jira_api.BaseAuthorizationRequest.baseAuthorizationRequest;
import static objects.steps.edu_jira_api.CreateIssueApi.createIssueApi;
import static objects.steps.edu_jira_api.TransitionByStatusesIssueApi.transitionByStatuses;
import static util.Config.getConfigValue;

@Epic(value = "Api Test")
@Feature(value = "ifellowEduJira.ru Tests")
@Story(value = "Api Jira")

public class ApiEduJiraTest extends RequestSpecificationAllTests {

    public RequestSpecification request = requestSpecificationAllTests(getConfigValue("Url"));

    public String nameCoToProject = "TEST";

    @Test
    @DisplayName("Test Open Url")
    @Tag("Api")
    @Tag("EduJira")
    public void testOpenUrl() {

        OpenUrl.openUrlApi(request);
    }

    @Test
    @DisplayName("Авторизация и получение sessionId")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorization() {

        AuthorizationSessionId.authorizationSessionId(request);

    }

    @Test
    @DisplayName("Открываем прект и получаем количество задач в проекте")
    @Tag("Api")
    @Tag("EduJira")
    public void testGoToProject() {

        request = baseAuthorizationRequest(request);

        String projectKey = GoToProject.getProjectKey(nameCoToProject, request);

        GoToProject.getCountIssuesInProject(projectKey, request);

    }

    @Test
    @DisplayName("Создаем задачу и переводим её по статусам")
    @Tag("Api")
    @Tag("EduJira")
    public void testCreateIssueAndTransitionByStatuses() {

        request = baseAuthorizationRequest(request);

        String issueId = createIssueApi(request);

        transitionByStatuses(request, issueId);
    }
}
