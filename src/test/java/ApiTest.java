import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertTrue;

public class ApiTest {
    private static final String  BASE_URL = "https://pokeapi.co/api/v2";
    private static final  String END_POINT_POKEMON = "pokemon";
    private static final  String PARAMS = "/?limit=3";
    Connect connect = new Connect(BASE_URL, END_POINT_POKEMON);

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testWeightComparison() {
        Response pidgeottoResponse = connect.getPokemonResponse("pidgeotto");
        Response rattataResponse = connect.getPokemonResponse("rattata");
        PokemonDTO pidgeotto = pidgeottoResponse
                .then()
                .statusCode(200)
                .extract()
                .as(PokemonDTO.class);
        PokemonDTO rattata = rattataResponse
                .then()
                .statusCode(200)
                .extract()
                .as(PokemonDTO.class);
        assertTrue(rattata.getWeight() < pidgeotto.getWeight(), "Вес rattata > pidgeotto");
    }

    @Test
    public void testRattataAbilitiesTK2() {
        Response rattataResponse = connect.getPokemonResponse("rattata");
        PokemonDTO rattata = rattataResponse
                .then()
                .statusCode(200)
                .extract()
                .as(PokemonDTO.class);
        boolean hasAbilities = rattata.getAbilities()
                .stream()
                .map(PokemonDTO.AbilityDTO::getAbility)
                .map(PokemonDTO.AbilityDTO.Ability::getName)
                .anyMatch(name -> name.equals("guts")) &&
                rattata.getAbilities()
                        .stream()
                        .map(PokemonDTO.AbilityDTO::getAbility)
                        .map(PokemonDTO.AbilityDTO.Ability::getName)
                        .anyMatch(name -> name.equals("run-away"));
        assertTrue(hasAbilities, "Не все способности найдены");
    }

    @Test
    public void testPokemonListTK3() {
        Response response = connect.get(PARAMS);
        List<String> pokemonNames = response.jsonPath().getList("results.name");
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(pokemonNames.size(), equalTo(3));
        assertThat(pokemonNames, everyItem(notNullValue()));
    }
}