package de.check24.ui.pages.auth;

import de.check24.ui.pages.base.BasePage;

import static de.check24.ui.driver.Driver.clickElementInShadowRoot;

public class AuthPage extends BasePage {
    private final String AUTH_SHADOW_HOST = "//unified-login";
    private final String ZURUCK_LINK_CSS = "div.c24-uli-back-init.c24-uli-back-browser";

    public void clickZuruck() {
        clickElementInShadowRoot(AUTH_SHADOW_HOST, ZURUCK_LINK_CSS);
    }
}
