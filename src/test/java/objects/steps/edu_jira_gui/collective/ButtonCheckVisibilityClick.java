package objects.steps.edu_jira_gui.collective;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;

import static hooks.WebHooks.saveScreenshot;

public class ButtonCheckVisibilityClick {

    @Step("Дожидаемся отображения кнопки \"{elementName}\" и нажимаем на неё")
    public static void buttonCheckVisibilityClick(@NotNull SelenideElement element, String elementName) {
        element.shouldBe(Condition.visible);
        saveScreenshot("Step Screenshot");
        assert element.is(Condition.visible) : elementName + " не отражается на странице или не кликабельна.";
        element.click();
    }
}
