import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest {
    private int weight;
    private Response response;
    private JsonPath jsonPath;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://pokeapi.co/api/v2";
        weight = given().contentType(ContentType.JSON)
                .when().get("/pokemon/pidgeotto")
                .then().extract().path("weight");
    }

    @Test
    public void testWeightTK1() {
        given().contentType(ContentType.JSON)
                .when().get("/pokemon/rattata")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("weight", lessThan(weight));
    }

    @Test
    public void testRattataAbilityTK2() {
        String url = "https://pokeapi.co/api/v2/pokemon/rattata";
        Response response = RestAssured.get(url);
        response.then().statusCode(200)
                .body("abilities.ability.name", hasItem("run-away"));
    }

    @Test
    public void testPokemonListTK3() {
        RestAssured.baseURI = "https://pokeapi.co/api/v2";
        given().
                queryParam("limit", 3).
                when().
                get("/pokemon").
                then().
                assertThat().
                statusCode(200).
                body("count", equalTo(1279)).
                body("results.size()", equalTo(3)).
                body("results.name", everyItem(notNullValue()));
    }
}
