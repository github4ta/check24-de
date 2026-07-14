package by.onliner.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeApiTest {
    HomeService homeService = new HomeService();

    @Test
    @DisplayName ("Get Home Data")
    public void testGetHomeData(){
        homeService.getHomeData();

        Assertions.assertEquals(200, homeService.getStatusCode());
    }
}
