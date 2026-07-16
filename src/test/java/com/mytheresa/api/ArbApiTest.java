package com.mytheresa.api;

import com.arbbot.api.RequestData;
import com.arbbot.api.health.Healthcheck;
import com.arbbot.api.symbols.SymbolController;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArbApiTest {
    String sym = "XRPUSDT";
    RequestData requestData = new RequestData(
            Map.of(
                    "accept", "application/json",
                    "Content-Type", "application/json"
            ),
            """
                    {
                      "symbol": "%s"
                    }
                    """.formatted(sym)
    );

    @Test
    @Order(1)
    public void CheckArbTest() {
        Healthcheck healthcheck = new Healthcheck();

        Response response = healthcheck.getResponse();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("ok", response.jsonPath().getString("status"));
        Assertions.assertEquals("1", response.jsonPath().getString("symbols"));
    }

    @Test
    @Order(2)
    public void checkPostRequestForBlackListTest() {

        Response response = new SymbolController()
                .setBlackListUrl()
                .setMethod(Method.POST)
                .setRequestData(requestData)
                .doRequest();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("true", response.jsonPath().getString("ok"));
    }

    @Test
    @Order(3)
    public void checkPresenceOfSymbolInBlacklistTest() {

        Response response = new SymbolController().doRequest();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.jsonPath()
                .getList("blacklist.userList").contains(sym));
    }

    @Test
    @Order(4)
    public void checkDeleteRequestForBlackListTest() {
        Response response = new SymbolController()
                .setUrl("http://52.194.254.164:8080/api/symbols/blacklist/" + sym)
                .setMethod(Method.DELETE)
                .doRequest();

        Assertions.assertEquals(200, response.statusCode());

        response = new SymbolController()
                .doRequest();

        Assertions.assertFalse(response.jsonPath().getList("blacklist.userList").contains(sym));
    }
}
