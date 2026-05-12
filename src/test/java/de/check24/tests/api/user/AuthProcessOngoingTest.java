package de.check24.tests.api.user;

import de.check24.api.user.AuthProcessOngoingService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AuthProcessOngoingTest {

    @Test
    public void checkAuthProcessOngoing() {

        String body = """
        login=navis1mplegod@gmail.com
        auth_type=pwreset_otp_v1
        uuid=
        ls=2
        product=check24_lp
        config_key=default
        loc=de_DE
        challenge=
        uli_context=kb
        csrf_token=3aa068d6eed2992bd7dd67f5ead4c51c394a85d0871ee068e38a7595d2582669.i7MevwWw_wmWRSVOlIJvCx7z-khWbJd-5TDDQGCYhPw
        csrf_validation=null
        deviceoutput=desktop
        x_test_env=null
        auth_api_group=b
        entrypoint=email
        account_exists=true
        client_hints={"architecture":"x86","brands":[{"brand":"Google Chrome","version":"149"},{"brand":"Chromium","version":"149"},{"brand":"Not)A;Brand","version":"24"}],"fullVersionList":[{"brand":"Google Chrome","version":"149.0.7827.3"},{"brand":"Chromium","version":"149.0.7827.3"},{"brand":"Not)A;Brand","version":"24.0.0.0"}],"mobile":false,"model":"","platform":"Windows","platformVersion":"10.0.0"}
        """.strip().replace("\n", "&");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36");
        headers.put("Cookie", "st={{vault:json-web-token}}; auth_api_group=b; __cf_bm=ujbc7S6yV1M1y9dT0N4cIa8_EMI5oRy73BiQ1UbrAGE-1778566340.6293242-1.0.1.1-Z1THcQeEgvARhpHMamDMGeGwNw5rtjSz_n_fVxVv5E_R37_YpVWYtJfKsfcuZ03joA_ptTeh8M5NG4OBTMf.21zvmaq63bULD8sFz.0efHSAwHlv2Lfb3wnKd0rvFSc.; csrf_token=3aa068d6eed2992bd7dd67f5ead4c51c394a85d0871ee068e38a7595d2582669.i7MevwWw_wmWRSVOlIJvCx7z-khWbJd-5TDDQGCYhPw");

        AuthProcessOngoingService service = new AuthProcessOngoingService();

        service.setHeaders(headers);
        service.setBody(body);
        service.doRequest();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(
                        service.getStatusCode())
                .isEqualTo(200);
        softAssertions.assertThat(
                        service.getPrepared())
                .isTrue();
        softAssertions.assertThat(
                        service.getAuthProcessType())
                .isEqualTo("pwreset_otp_v1");
        softAssertions.assertAll();
    }

}



