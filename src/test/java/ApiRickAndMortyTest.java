import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.RequestSpecificationAllTests;
import objects.steps.rick_and_morty_api.GetCharacter;
import objects.steps.rick_and_morty_api.OpenUrlRickAndMorty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static objects.steps.rick_and_morty_api.ComparingCharacters.comparingCharacters;
import static objects.steps.rick_and_morty_api.GetCharacter.getCharacter;
import static objects.steps.rick_and_morty_api.GetEpisode.getEpisode;
import static util.Config.getConfigValue;


@Epic(value = "Api Test")
@Feature(value="RickAndMortyApi.com Tests")
public class ApiRickAndMortyTest extends RequestSpecificationAllTests {

    private final RequestSpecification request = requestSpecificationAllTests(getConfigValue("UrlRickAndMortyApi"));

    private String characterId = "2";

    @Test
    @Story("Open Url")
    @DisplayName("Проверка доступности сайта RickAndMortyApi")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testOpenUrl() {

        OpenUrlRickAndMorty.openUrlRickAndMortyApi(request);
    }

    @Test()
    @Story("Comparing Characters")
    @DisplayName("Получение и cравнение данных персонажей")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testComparingCharacters() {

        GetCharacter getCharacter1 = getCharacter(characterId, request);

        String lastEpisodeNumber = getCharacter1.getLastEpisodeNumber();

        characterId = getEpisode(lastEpisodeNumber, request);

        GetCharacter getCharacter2 = getCharacter(characterId, request);

        comparingCharacters(getCharacter1, getCharacter2);

    }
}
