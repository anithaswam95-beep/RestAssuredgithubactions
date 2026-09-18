package automation.Rest;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostMethod {
@Test 
    public void createResource() {
        
        String requestPayload = """
            {
                "firstname": "Anitha",
                "lastname": "Swaminathan",
                "totalprice": 1000,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2026-09-01",
                    "checkout": "2026-09-01"
                },
                "additionalneeds": "Breakfast"
            }
            """;

        Response postResponse = given() 
            .contentType("application/json")
            .body(requestPayload)
        .when()
            .post("https://restful-booker.herokuapp.com/booking");
     // 1. Status Code Print
        System.out.println("Status Code is " + postResponse.getStatusCode());

        // Validations
        // 1. Validation of status code
        Assert.assertEquals(postResponse.getStatusCode(), 200, "Status code is not 200");

        // 2. Content Type Header validation
        Assert.assertTrue(postResponse.contentType().contains("application/json"));
     // 3. Response body validation
        Assert.assertFalse(postResponse.getBody().asString().isEmpty(), "Body is Empty");

        // 4. Validate booking id
        int bookingidReceived = postResponse.jsonPath().getInt("bookingid");
        System.out.println("Booking id: " + bookingidReceived);
        
     

        Assert.assertTrue(bookingidReceived > 0, "Booking is not generated");

        // 5. Last Name validation
        Assert.assertEquals(postResponse.jsonPath().getString("booking.lastname"), "Swaminathan");

        // 6. Checkin validation
        Assert.assertEquals(postResponse.jsonPath().getString("booking.bookingdates.checkin"), "2026-09-01");
        
        postResponse.prettyPrint();
    }

    }



