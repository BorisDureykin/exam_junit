package objects;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.RequestSpecificationAllTests;
import objects.steps.rick_and_morty_api.GetCharacter;
import objects.steps.rick_and_morty_api.GetEpisode;
import objects.steps.rick_and_morty_api.OpenUrlRickAndMorty;
import objects.steps.rick_and_morty_api.СomparingСharacters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static util.Config.getConfigValue;


@Epic(value = "Api Test")
@Feature(value="RickAndMortyApi.com Tests")
@Story(value = "Api Rick And Morty")
public class ApiRickAndMortyTest extends RequestSpecificationAllTests {

    public RequestSpecification request = requestSpecificationAllTests(getConfigValue("UrlRickAndMortyApi"));

    public String characterId = "2";

    @Test
    @DisplayName("Проверка доступности сайта RickAndMortyApi")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testOpenUrl() {

        OpenUrlRickAndMorty.openUrlRickAndMortyApi(request);
    }

    @Test()
    @DisplayName("Получение и cравнение данных персонажей")
    @Tag("Api")
    @Tag("RickAndMorty")
    public void testGetCharacter() {

        GetCharacter getCharacter1 = GetCharacter.getCharacter(characterId, request);

        String lastEpisodeNumber = getCharacter1.getLastEpisodeNumber();

        String characterId2 = GetEpisode.getEpisode(lastEpisodeNumber, request);

        GetCharacter getCharacter2 = GetCharacter.getCharacter(characterId2, request);

        СomparingСharacters.comparingСharacters(getCharacter1, getCharacter2);

    }
}
