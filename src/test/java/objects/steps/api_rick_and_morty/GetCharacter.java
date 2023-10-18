package objects.steps.api_rick_and_morty;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.api_all_request_respone.ResponseAllTests;
import org.json.JSONObject;

import static hooks.WebHooks.saveMessage;


public class GetCharacter extends ResponseAllTests {

    private final String species;
    private final String location;
    private final String lastEpisodeNumber;

    public GetCharacter(String species, String location, String lastEpisodeNumber) {

        this.species = species;
        this.location = location;
        this.lastEpisodeNumber = lastEpisodeNumber;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getLastEpisodeNumber() {
        return lastEpisodeNumber;
    }


    @Step("Получение данных персонажа с id : \"{id}\"")
    public static GetCharacter getCharacter(String id, RequestSpecification request) {

        String endpoint = "/character/" + id;

        String pathSchema = "rick_and_morti/schemaGetCharacter.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        JSONObject jsonObject = new JSONObject(responseBody);

        String species = jsonObject.getString("species");

        String location = jsonObject.getJSONObject("location").getString("name");

        String lastEpisodeUrl = jsonObject.getJSONArray("episode").getString(jsonObject.getJSONArray("episode").length() - 1);

        String lastEpisodeNumber = lastEpisodeUrl.substring(lastEpisodeUrl.lastIndexOf("/") + 1);

        String message = "Персонаж ID: "+id + " раса персонажа: "+ species + ", место нахождения персонажа: "+ location  + ", последний эпизод где появлялся персонаж: "+ lastEpisodeNumber;

        saveMessage("Характеристики персонажа" ,message);

        return new GetCharacter(species, location, lastEpisodeNumber);
    }


}
