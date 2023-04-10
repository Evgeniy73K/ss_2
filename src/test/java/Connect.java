import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Connect {
    private String baseURL;
    private String endPoint;
    private String params;


    public Connect(String baseURL, String endPoint) {
        this.baseURL = baseURL;
        this.endPoint = endPoint;
    }

    public Connect(String baseURL, String endPoint, String params) {
        this.baseURL = baseURL;
        this.endPoint = endPoint;
        this.params = params;
    }

    public Response getPokemonResponse(String value) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURL + "/"+endPoint+"/"+value);
        return response;
    }
    public Response get(String params) {
        return given()
                .baseUri(baseURL)
                .when()
                .get("/"+endPoint+"/"+params)
                .then()
                .extract()
                .response();
    }
}
