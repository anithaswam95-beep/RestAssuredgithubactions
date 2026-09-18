package automation.Rest;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class AuthMethod {

    public String authMethod() {

        String authPayload = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;

        Response authresponse = given()
                .contentType("application/json")
                .body(authPayload)
                .when()
                .post("https://restful-booker.herokuapp.com/auth");

        String token = authresponse.jsonPath().getString("token");

        System.out.println(token);

        authresponse.prettyPrint();

        return token;
    }
}


