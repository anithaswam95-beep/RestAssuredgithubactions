package automation.Rest;



import static io.restassured.RestAssured.*;
 import org.testng.annotations.Test;

public class GetMethod {
    @Test
    
    public void getUsers() {
        given()                 // Precondition
            .baseUri("https://restful-booker.herokuapp.com/booking")
        .when()                 // Action
            .get()
        .then()                 // Assertion
            .statusCode(200)
            .log().body();
    }
}


