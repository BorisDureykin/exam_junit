package objects.steps.rick_and_morty_api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static objects.steps.request_respone_api.RequestSpecificationAllTests.requestSpecificationAllTests;
import static objects.steps.rick_and_morty_api.GetCharacter.getCharacter;
import static objects.steps.rick_and_morty_api.GetEpisode.getEpisode;
import static util.Config.getConfigValue;

public class ComparingCharacters {

    static GetCharacter getCharacter1;
    static GetCharacter getCharacter2;
    public static void getDataCharacter(String keyUrl, String characterId){

       RequestSpecification request = requestSpecificationAllTests(getConfigValue(keyUrl));

        getCharacter1 = getCharacter(characterId, request);

        String lastEpisodeNumber = getCharacter1.getLastEpisodeNumber();

        characterId = getEpisode(lastEpisodeNumber, request);

        getCharacter2 = getCharacter(characterId, request);


    }

    @Step("Сравнение данных персонажей")
    public static void comparingCharacters() {

        String species = getCharacter1.getSpecies();

        String location = getCharacter1.getLocation();

        String species2 = getCharacter2.getSpecies();

        String location2 = getCharacter2.getLocation();

//        assertAll();

        String message = species.equals(species2) ? "Принадлежат к одной рассе: " + species : "Рассы разные: " + species + " и " + species2;

        System.out.println(message);

        message = location.equals(location2) ? "Находятся в одной локации: " + location : "Локации разные: " + location + " и " + location2;

        System.out.println(message);

    }

}
