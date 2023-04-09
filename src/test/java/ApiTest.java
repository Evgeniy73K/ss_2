import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertTrue;

public class ApiTest {
    private static final String BASE_URL = "https://pokeapi.co/api/v2";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testWeightComparison() {
        Response pidgeottoResponse = given().contentType(ContentType.JSON).when().get("/pokemon/pidgeotto");
        Response rattataResponse = given().contentType(ContentType.JSON).when().get("/pokemon/rattata");
        PokemonDTO pidgeotto = pidgeottoResponse.then().statusCode(200).extract().as(PokemonDTO.class);
        PokemonDTO rattata = rattataResponse.then().statusCode(200).extract().as(PokemonDTO.class);
        assertTrue(rattata.getWeight() < pidgeotto.getWeight(), "Вес rattata > pidgeotto");
    }


    @Test
    public void testRattataAbilitiesTK2() {
        Response rattataResponse = given().contentType(ContentType.JSON).when().get("/pokemon/rattata");
        PokemonDTO rattata = rattataResponse.then().statusCode(200).extract().as(PokemonDTO.class);
        boolean hasGutsAbility = rattata.getAbilities().stream().map(PokemonDTO.AbilityDTO::getAbility).map(PokemonDTO.AbilityDTO.Ability::getName).anyMatch(name -> name.equals("guts"));
        boolean hasRunAwayAbility = rattata.getAbilities().stream().map(PokemonDTO.AbilityDTO::getAbility).map(PokemonDTO.AbilityDTO.Ability::getName).anyMatch(name -> name.equals("guts"));
        assertTrue(hasGutsAbility && hasRunAwayAbility, "Не все спопсобности найдены");
    }


    @Test
    public void testPokemonListTK3() {
        given().queryParam("limit", 3).when().get("/pokemon").then().assertThat().statusCode(200).body("results.size()", equalTo(3)).body("results.name", everyItem(notNullValue()));
    }
}