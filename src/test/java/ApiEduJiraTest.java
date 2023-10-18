import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import objects.steps.api_all_request_respone.RequestSpecificationAllTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.api_all_request_respone.OpenUrlApi.openUrlApi;
import static objects.steps.api_edu_jira.AuthorizationSessionId.authorizationSessionId;
import static objects.steps.api_edu_jira.CreateIssueApi.createIssueApi;
import static objects.steps.api_edu_jira.GoToProjectCountIssueApi.getCountIssuesInProjectApi;
import static objects.steps.api_edu_jira.GoToProjectCountIssueApi.getProjectKey;
import static objects.steps.api_edu_jira.TransitionByStatusesIssueApi.transitionByStatuses;
import static util.Config.getConfigValue;

@Epic(value = "Api Test")
@Feature(value = "ifellowEduJira.ru Tests")
public class ApiEduJiraTest extends RequestSpecificationAllTests {

    private final String keyUrl = "UrlIfellowJira";
    private String login = getConfigValue("login");
    private String password = getConfigValue("password");
    private String nameCoToProject = "TEST";
    private String endpoint;
    private String method;
    private String statusCode;
    private String pathSchema;

    @Test
    @DisplayName("Test Open Url")
    @Story("Open Url")
    @Tag("Api")
    @Tag("EduJira")
    public void testOpenUrl() {

        endpoint = "/rest/api/2/resolution";

        method = "GET";

        statusCode = "200";

        pathSchema = "ifellow_edu_jira/schemaOpenUrl.json";

        openUrlApi(keyUrl, endpoint, method, statusCode, pathSchema);
    }

    @Test
    @DisplayName("Авторизация позитивный кейс")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationPositive() {

        endpoint = "/rest/auth/1/session";

        method = "POST";

        statusCode = "200";

        pathSchema = "ifellow_edu_jira/schemaAuth.json";

        authorizationSessionId(keyUrl, login, password, endpoint, method, statusCode, pathSchema);

    }

    @Test
    @DisplayName("Авторизация Неверный логин")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationNegativeLogin() {

        login = "Ne tot";

        endpoint = "/rest/auth/1/session";

        method = "POST";

        statusCode = "401";

        authorizationSessionId(keyUrl, login, password, endpoint, method, statusCode, null);
    }

    @Test
    @DisplayName("Авторизация Неверный пароль")
    @Story("Authorization")
    @Tag("Api")
    @Tag("EduJira")
    public void testAuthorizationNegativePassword() {

        password = "QQQQQQQ";

        endpoint = "/rest/auth/1/session";

        method = "POST";

        statusCode = "401";

        authorizationSessionId(keyUrl, login, password, endpoint, method, statusCode, null);
    }

    @Test
    @DisplayName("Открываем прект и получаем количество задач в проекте")
    @Story("Go To Project")
    @Tag("Api")
    @Tag("EduJira")
    public void testGoToProject() {

        endpoint = "/rest/api/2/project/";

        method = "GET";

        statusCode = "200";

        pathSchema = "ifellow_edu_jira/schemaGetProjectKey.json";

        getProjectKey(nameCoToProject, endpoint, method, statusCode, pathSchema);

        endpoint = "/rest/api/2/search";

        method = "GET";

        statusCode = "200";

        pathSchema = "ifellow_edu_jira/schemaSearch.json";

        getCountIssuesInProjectApi(endpoint, method, statusCode, pathSchema);
    }

    @Test
    @DisplayName("Создаем задачу и переводим её по статусам")
    @Story("Create Issue")
    @Tag("Api")
    @Tag("EduJira")
    public void testCreateIssueAndTransitionByStatuses() {

        endpoint = "/rest/api/2/issue";

        method = "POST";

        statusCode = "201";

        pathSchema = "ifellow_edu_jira/schemaCreateIssue.json";

        createIssueApi(endpoint, method, statusCode, pathSchema);

        transitionByStatuses();
    }
}
