package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static hooks.WebHooks.saveScreenshot;
import static objects.elements.EdujiraIfellowRuLogin.bodyPage;
import static objects.elements.EdujiraIfellowRuLogin.titlePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenUrl {

    @Step("Открываем: {url}")
    public static void openUrl(String url) {

        open(url);
        getWebDriver().manage().window().maximize();
        assertTrue(bodyPage.shouldBe(Condition.enabled).exists(), "Body нет на странице.");
        saveScreenshot("Открываем: " + url);
    }

    @Step("Проверяем URL, ожидаемое значение: {url}, и TitlePage, ожидаемое значение: {pageTitle}")
    public static void checkUrlAndTitlePage(String url, String pageTitle) {

        assertTrue(titlePage.shouldBe(Condition.hidden).getOwnText().contains(pageTitle), "Заголовок страницы не содержит: "+pageTitle);
        assertTrue(WebDriverRunner.url().contains(url), "Не верный URL, ожидаемое значение: "+url);
        saveScreenshot("Проверяем URL, ожидаемое значение: " + url+", и TitlePage, ожидаемое значение: " +pageTitle);
    }
}