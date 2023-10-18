package objects.steps.api_rick_and_morty;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static hooks.WebHooks.saveMessage;
import static io.qameta.allure.Allure.step;
import static objects.steps.api_all_request_respone.RequestSpecificationAllTests.requestSpecificationAllTests;
import static objects.steps.api_rick_and_morty.GetCharacter.getCharacter;
import static objects.steps.api_rick_and_morty.GetEpisode.getEpisode;
import static util.Config.getConfigValue;

public class ComparingCharacters {

   private static RequestSpecification request;
    private static GetCharacter getCharacter1;
    private static GetCharacter getCharacter2;
    private static String lastEpisodeNumber;
    private static String characterId2;


    @Step("Получение характеристик персонажа с ID: {characterId}")
    public static void getDataCharacter1(String keyUrl, String characterId){
        request = requestSpecificationAllTests(getConfigValue(keyUrl));
        getCharacter1 = getCharacter(characterId, request);
    }

    @Step("получили номер последнего эпизода")
    public static void getLastEpisodeNumber(){
        lastEpisodeNumber = getCharacter1.getLastEpisodeNumber();
        String message = "Номер последнего эпизода где появлялся персонаж: "+lastEpisodeNumber;
        saveMessage("Номер последнего эпизода" ,message);
    }

    public static void getLastCharacterId() {
        step("Получение номер последнего персонажа в эпизоде: \"" + lastEpisodeNumber, () -> {
            characterId2 = getEpisode(lastEpisodeNumber, request);
            String message = "В эпизоде: "+lastEpisodeNumber+ "Номер последнего персонажа: "+characterId2;
            saveMessage("Номер последнего персонажа" ,message);
        });
    }


    @Step("Получение характеристик персонажа с ID: {characterId2}")
    public static void getDataCharacter2(){

        getCharacter2 = getCharacter(characterId2, request);


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
