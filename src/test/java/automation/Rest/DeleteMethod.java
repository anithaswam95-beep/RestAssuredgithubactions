package automation.Rest;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteMethod {
@Test
    public void deleteMethod() {

        // Creating object for CreateID
        CreateID post = new CreateID();

        int bookingID = post.postMethod();

        // Creating object for AuthMethod
        AuthMethod auth = new AuthMethod();

        String token = auth.authMethod();

        Response deleteResponse = given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingID);

        deleteResponse.prettyPrint();
    }
}