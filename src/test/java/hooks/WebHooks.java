package hooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;

public class WebHooks {

    @Attachment(value = "{nameScreenshot}", type = "image/png")
    public static byte[] saveScreenshot(String nameScreenshot) {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "{nameMessage}", type = "text/plain")
    public static String saveMessage(String nameMessage, String message) {
        return message;
    }

    @BeforeEach
    public void allureSelenideListener(TestInfo testInfo) {
        if (testInfo.getTags().contains("GUI")) {
            String listenerName = "Allureselenide";
            if (SelenideLogger.hasListener(listenerName)) {
                SelenideLogger.addListener(listenerName,
                        (new AllureSelenide()
                                .screenshots(true)
                                .savePageSource(false)));
            }
        }
    }

    @AfterEach
    public void afterClass(TestInfo testInfo) {
        if (testInfo.getTags().contains("GUI")) {
            saveScreenshot("Screenshot After Test ");
            WebDriverRunner.closeWebDriver();
            SelenideLogger.removeListener("Allureselenide");
        }
    }
}
