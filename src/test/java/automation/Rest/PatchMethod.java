package automation.Rest;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PatchMethod {

    @Test
    public void patchMethod() {

        // Creating object for CreateID
        CreateID post = new CreateID();

        int bookingID = post.postMethod();

        // Creating object for AuthMethod
        AuthMethod auth = new AuthMethod();

        String token = auth.authMethod();

        String reqPatchPayload = """
                {
                    "firstname" : "AnithaUpdated",
                    "lastname" : "SwaminathanUpdated"
                }
                """;

        Response patchResponse = given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
                .body(reqPatchPayload)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/" + bookingID);

        patchResponse.prettyPrint();
    }
}