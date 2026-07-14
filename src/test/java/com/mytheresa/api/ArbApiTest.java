package com.mytheresa.api;

import com.arbbot.api.health.Healthcheck;
import com.arbbot.api.symbols.SymbolController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArbApiTest {

    String sym = "XRPUSDT";

    @Test
    @Order(1)
    public void CheckArbTest() throws MalformedURLException, URISyntaxException {
        Healthcheck healthcheck = new Healthcheck();

        Assertions.assertEquals(200, healthcheck.getStatusCode());
        Assertions.assertEquals("ok", healthcheck.getResponse().body().jsonPath().getString("status"));
        Assertions.assertEquals("1", healthcheck.getResponse().body().jsonPath().getString("symbols"));
    }


    @Test
    @Order(2)
    public void checkPostRequestForBlackListTest() throws URISyntaxException {
        SymbolController symbolController = new SymbolController();

        Assertions.assertEquals(200, symbolController.addToBlacklist(sym).statusCode());
    }

    @Test
    @Order(3)
    public void checkPresenceOfSymbolInBlacklistTest() throws URISyntaxException {
        SymbolController symbolController = new SymbolController();
        var response = symbolController.getResponse();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.jsonPath()
                .getList("blacklist.userList").contains(sym));
    }

    @Test
    @Order(4)
    public void checkDeleteRequestForBlackListTest() throws URISyntaxException {
        SymbolController symbolController = new SymbolController();


        var response = symbolController.deleteFromBlacklist(sym);
        Assertions.assertEquals(200, response.statusCode());

        response =symbolController.getResponse();
        Assertions.assertFalse(response.jsonPath()
                .getList("blacklist.userList").contains(sym));
    }
}
