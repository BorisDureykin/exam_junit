package objects.steps.rick_and_morty_api;

import io.qameta.allure.Step;

public class ComparingCharacters {

    @Step("Сравнение данных персонажей")
    public static void comparingCharacters(GetCharacter getCharacter1, GetCharacter getCharacter2) {

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
