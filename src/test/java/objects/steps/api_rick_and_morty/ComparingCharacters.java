package objects.steps.api_rick_and_morty;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static hooks.WebHooks.saveMessage;
import static objects.steps.api_all_request_respone.RequestSpecificationAllTests.requestSpecificationAllTests;
import static objects.steps.api_rick_and_morty.GetCharacter.getCharacter;
import static objects.steps.api_rick_and_morty.GetEpisode.getEpisode;
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

        String messageSpecies = species.equals(species2) ? "Принадлежат к одной расе: " + species : "Расы разные: " + species + " и " + species2;
        saveMessage("Принадлежность к расам" ,messageSpecies);
        String messageLocation = location.equals(location2) ? "Находятся в одной локации: " + location : "Локации разные: " + location + " и " + location2;
        saveMessage("Локации персонажей" ,messageLocation);

    }

}
