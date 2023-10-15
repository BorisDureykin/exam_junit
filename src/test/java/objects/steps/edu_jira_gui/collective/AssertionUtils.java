package objects.steps.edu_jira_gui.collective;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static hooks.WebHooks.saveScreenshot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertionUtils {

    public static void assertEqualUtils(String expected, String actual, String message) {
        try {

            assertEquals(expected, actual, message);
        } catch (AssertionError e) {
            saveScreenshot(message);
            throw e;
//            step("Шаг провален: " + message, Status.FAILED);
        }
    }

    public static void assertTrueContains(String expected, String actual, String message) {
        try {

            assertTrue(actual.contains(expected), message);        } catch (AssertionError e) {
            saveScreenshot(message);
            throw e;
        }
    }

    public static void assertTrueVisible(SelenideElement element, String message) {
        try {
            element.shouldBe(Condition.visible);
            assertTrue(element.is(Condition.visible), message);
        } catch (AssertionError e) {
            saveScreenshot(message);
            throw e;
        }
    }

}
