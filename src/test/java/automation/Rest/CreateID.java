package automation.Rest;



import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class CreateID {

    public int postMethod() {
        String requestPayload = """
            {
                "firstname" : "Anitha",
                "lastname" : "Swaminathan",
                "totalprice" : 1000,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2026-09-01",
                    "checkout" : "2026-09-01"
                },
                "additionalneeds" : "Breakfast"
            }
            """;

        Response response = given()
                .contentType("application/json")
                .body(requestPayload)
            .when()
                .post("https://restful-booker.herokuapp.com/booking");

        int bookingid = response.jsonPath().getInt("bookingid");
        return bookingid;
    }
}


