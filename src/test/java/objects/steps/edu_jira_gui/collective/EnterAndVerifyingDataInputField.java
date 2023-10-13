package objects.steps.edu_jira_gui.collective;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import static hooks.WebHooks.saveScreenshot;


public class EnterAndVerifyingDataInputField {

    @Step("Дожидаемся отображения поля ввода \"{elementName}\" и вводим в него \"{valueElement}\"")
    public static void enterAndVerifyingDataInputField(@NotNull SelenideElement element, String valueElement, String elementName, char key) {

        element.shouldBe(Condition.visible).sendKeys(valueElement);
        saveScreenshot("Step Screenshot");
        Assertions.assertEquals(valueElement, element.getValue(), "В поле " + elementName + " Введено неверное значение.");

        if (key == '1') {
            element.pressEnter();
        }
    }
}
