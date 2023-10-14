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

import static objects.steps.edu_jira_api.AuthorizationSessionId.authorizationSessionId;
import static objects.steps.edu_jira_api.BaseAuthorizationRequest.baseAuthorizationRequest;
import static objects.steps.edu_jira_api.CreateIssueApi.createIssueApi;
import static objects.steps.edu_jira_api.TransitionByStatusesIssueApi.transitionByStatuses;
import static util.Config.getConfigValue;

@Epic(value = "Api Test")
@Feature(value = "ifellowEduJira.ru Tests")
public class ApiEduJiraTest extends RequestSpecificationAllTests {

    private RequestSpecification request = requestSpecificationAllTests(getConfigValue("Url"));
    private String nameCoToProject = "TEST";
    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private String endpoint;
    private String statusCode;


    @Test
    @DisplayName("Test Open Url")
    @Story("Open Url")
    @Tag("Api")
    @Tag("EduJira")
    public void testOpenUrl() {

        OpenUrl.openUrlApi(request);
    }

    @Test
    @DisplayName("Авторизация позитивный кейс")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationPositive() {

        endpoint = "/rest/auth/1/session";
        statusCode = "200";

        authorizationSessionId(request, login, password, endpoint, statusCode);

    }

    @Test
    @DisplayName("Авторизация Неверный логин")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationNegativeLogin() {

        login = "Ne tot";
        endpoint = "/rest/auth/1/session";
        statusCode = "401";

        authorizationSessionId(request, login, password, endpoint, statusCode);
    }

    @Test
    @DisplayName("Авторизация Неверный пароль")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationNegativePassword() {

        password = "QQQQQQQ";
        endpoint = "/rest/auth/1/session";
        statusCode = "401";

        authorizationSessionId(request, login, password, endpoint, statusCode);
    }



    @Test
    @DisplayName("Открываем прект и получаем количество задач в проекте")
    @Story("Go To Project")
    @Tag("Api")
    @Tag("EduJira")
    public void testGoToProject() {

        request = baseAuthorizationRequest(request);

        String projectKey = GoToProject.getProjectKey(nameCoToProject, request);

        GoToProject.getCountIssuesInProject(projectKey, request);

    }

    @Test
    @DisplayName("Создаем задачу и переводим её по статусам")
    @Story("Create Issue")
    @Tag("Api")
    @Tag("EduJira")
    public void testCreateIssueAndTransitionByStatuses() {

        request = baseAuthorizationRequest(request);

        String issueId = createIssueApi(request);

        transitionByStatuses(request, issueId);
    }
}
