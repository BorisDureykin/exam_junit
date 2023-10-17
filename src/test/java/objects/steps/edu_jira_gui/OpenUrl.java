package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.EdujiraIfellowRuLogin.bodyPage;
import static objects.elements.EdujiraIfellowRuLogin.titlePage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Config.getConfigValue;

public class OpenUrl {

    public static void openUrl(String keyUrl) {

        String url = getConfigValue(keyUrl);
        step("Открываем: " + url, () -> {
            open(url);
            getWebDriver().manage().window().maximize();
            assertTrue(bodyPage.shouldBe(Condition.enabled).exists(), "Body нет на странице.");
            saveScreenshot("Открываем: " + url);
        });
    }

    public static void checkUrlAndTitlePage(String keyUrl, String pageTitle) {

        String url = getConfigValue(keyUrl);
        step("Проверяем URL, ожидаемое значение: " + url + ", и TitlePage, ожидаемое значение: " + pageTitle, () -> {
            assertTrue(titlePage.shouldBe(Condition.hidden).getOwnText().contains(pageTitle), "Заголовок страницы не содержит: " + pageTitle);
            assertTrue(WebDriverRunner.url().contains(url), "Не верный URL, ожидаемое значение: " + url);
            saveScreenshot("Проверяем URL, ожидаемое значение: " + url + ", и TitlePage, ожидаемое значение: " + pageTitle);
        });
    }
}