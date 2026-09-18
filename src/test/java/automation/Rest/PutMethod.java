package automation.Rest;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;
public class PutMethod {
@Test
    public void putMethod() {

        CreateID post = new CreateID();

        int bookingID = post.postMethod();

        AuthMethod auth = new AuthMethod();

        String token = auth.authMethod();

        String reqPutPayload = """
                {
                    "firstname" : "Anitha",
                    "lastname" : "Swaminathan",
                    "totalprice" : 1000,
                    "depositpaid" : false,
                    "bookingdates" : {
                        "checkin" : "2026-09-01",
                        "checkout" : "2026-09-02"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;

        Response putResponse = given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
                .body(reqPutPayload)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingID);

        putResponse.prettyPrint();
    }
}
    



