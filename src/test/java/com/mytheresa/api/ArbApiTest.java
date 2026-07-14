package com.mytheresa.api;

import com.arbbot.api.health.Healthcheck;
import com.arbbot.api.symbols.SymbolController;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArbApiTest {

    String sym = "XRPUSDT";

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
        SymbolController symbolController = new SymbolController();

        Assertions.assertEquals(200, symbolController.addToBlacklist(sym).statusCode());
    }

    @Test
    @Order(3)
    public void checkPresenceOfSymbolInBlacklistTest() {
        SymbolController symbolController = new SymbolController();
        Response response = symbolController.getResponse();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.jsonPath()
                .getList("blacklist.userList").contains(sym));
    }

    @Test
    @Order(4)
    public void checkDeleteRequestForBlackListTest() {
        SymbolController symbolController = new SymbolController();

        Response response = symbolController.deleteFromBlacklist(sym);
        Assertions.assertEquals(200, response.statusCode());

        response = symbolController.getResponse();
        Assertions.assertFalse(response.jsonPath()
                .getList("blacklist.userList").contains(sym));
    }
}
