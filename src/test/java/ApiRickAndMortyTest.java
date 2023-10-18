import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import objects.steps.api_all_request_respone.RequestSpecificationAllTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.api_all_request_respone.OpenUrlApi.openUrlApi;
import static objects.steps.api_rick_and_morty.ComparingCharacters.*;


@Epic(value = "Api Test")
@Feature(value="RickAndMortyApi.com Tests")
public class ApiRickAndMortyTest extends RequestSpecificationAllTests {
    private final String keyUrl = "UrlRickAndMortyApi";
    private String method;
    private String statusCode;
    private String pathSchema;
    private String characterId = "2";

    @Test
    @Story("Open Url")
    @DisplayName("Проверка доступности сайта RickAndMortyApi")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testOpenUrl() {

        pathSchema = "rick_and_morti/schemaOpenUrl.json";

        method = "GET";

        statusCode="200";

        openUrlApi(keyUrl, null, method, statusCode, pathSchema);

    }

    @Test()
    @Story("Comparing Characters")
    @DisplayName("Получение и cравнение данных персонажей")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testComparingCharacters() {

        getDataCharacter1(keyUrl, characterId);//данные 1 персонажа
        getLastEpisodeNumber(); //получили номер последнего эпизода
        getLastCharacterId();//получили номер последнего персонажа в эпизоде
        getDataCharacter2();//данные 2 персонажа
        comparingCharacters();

    }
}
