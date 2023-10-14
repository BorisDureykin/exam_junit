package objects.steps.edu_jira_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static hooks.WebHooks.saveScreenshot;
import static io.qameta.allure.Allure.step;
import static objects.elements.EdujiraIfellowRuLogin.bodyPage;
import static objects.elements.EdujiraIfellowRuLogin.titlePage;
import static util.Config.getConfigValue;

public class OpenUrl {

    public static void openUrl(String url) {

        step("Открываем: " + url , () -> {
            open(url);
            getWebDriver().manage().window().maximize();
            saveScreenshot("Step Screenshot");

            Assertions.assertTrue(bodyPage.shouldBe(Condition.enabled).exists(), "Body нет на странице.");
        });
    }

    public static void checkUrlAndTitlePage(String url, String pageTitle) {

        step("Проверяем URL: " + url+", и TitlePage: " +pageTitle, () -> {
            saveScreenshot("Step Screenshot");
            Assertions.assertTrue(titlePage.shouldBe(Condition.hidden).getOwnText().contains(pageTitle), "Заголовок страницы не содержит 'System Dashboard - Jira'");
            Assertions.assertTrue(WebDriverRunner.url().contains(url), "Не верный URL");
        });
    }
}