package de.check24.ui.pages.auth;

import static de.check24.ui.driver.Driver.click;
import static de.check24.ui.driver.Driver.getElementInShadowRoot;

import de.check24.ui.pages.base.BasePage;

public class AuthPage extends BasePage {
    private final String AUTH_SHADOW_HOST = "//unified-login";
    private final String ZURUCK_LINK_CSS = "div.c24-uli-back-init.c24-uli-back-browser";

    public void clickZuruck() {
        click(getElementInShadowRoot(AUTH_SHADOW_HOST, ZURUCK_LINK_CSS));
    }

}
