package de.check24.tests.api.user;

import de.check24.api.user.FavouriteService;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;

import java.util.HashMap;
import java.util.Map;

public class FavouriteTest {
    static Map<String, String> COOKIES = new HashMap<>();
    static Map<String, String> FORM_DATA = new HashMap<>();

    static {
        COOKIES.put("bpm", "5ba264694bae618205d8133aa97012e9815c150b");
        FORM_DATA.put("change", "1");
    }

    @Test
    public void checkGetFavouriteTest() {
        FavouriteService service = new FavouriteService();

        service.setCookies(COOKIES)
                .getFavouriteCount();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(
                        service.getStatusCode())
                .isEqualTo(200);

        softAssertions.assertThat(
                        service.hasKey("data.count"))
                .isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void checkSetFavouriteTest() {
        FavouriteService service = new FavouriteService();

        service.setCookies(COOKIES)
                .setFormData(FORM_DATA)
                .setFavouriteCount();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(
                service.getStatusCode())
                .isEqualTo(200);

        softAssertions.assertThat(
                service.hasKey("data.count"))
                .isTrue();
        softAssertions.assertAll();
    }
}
