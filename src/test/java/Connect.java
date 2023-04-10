import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Connect {
    private String baseURL;

    public Connect(String baseURL) {
        this.baseURL = baseURL;
    }

    public Response getPokemonResponse(String value) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURL + "/pokemon/"+value);
        return response;
    }
}
