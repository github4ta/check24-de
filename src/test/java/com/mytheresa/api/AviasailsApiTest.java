package com.mytheresa.api;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AviasailsApiTest {

    @Test
    public void flexibleDatesTicketsResultsTest() {

        String body = """
                {
                    "language": "ru",
                    "datacenter": "gp.eu-north-1",
                    "id": "2fe1c5f2-7c18-42b0-8abe-60fab815a1da"
                }
                """;

        given()
                .header("accept", "application/json")
                .contentType("application/json")
                .body(body)
                .when()
                .post("https://explore-api.aviasales.com/api/v1/flexible-dates/tickets/results")
                .then()
                .statusCode(200)
                .body("tickets[0].ticket.value",both(greaterThan(50_000.0F)).and(lessThan(100_000.0F)));
    }
}
