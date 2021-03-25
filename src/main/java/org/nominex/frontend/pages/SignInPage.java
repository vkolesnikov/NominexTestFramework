package org.nominex.frontend.pages;

import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class SignInPage extends BasePage {

    private final static String SIGN_IN_PAGE_URL = "/signin/";

    private final String LOGIN_FIELD_XPATH = "//input[@placeholder = 'you@example.com']";
    private final String PASSWORD_FIELD_XPATH = "//input[@type = 'password']";
    private final String SIGN_IN_BUTTON_XPATH = "//sl-button[text() =' Sign in ']";

    @Override
    public String getExpectedURL() {
        return SIGN_IN_PAGE_URL;
    }

    public SignInPage fillLoginField(String login) {
        $x(LOGIN_FIELD_XPATH).setValue(login);
        return this;
    }

    public SignInPage fillPasswordField(String password) {
        $x(PASSWORD_FIELD_XPATH).setValue(password);
        return this;
    }

    public SignInPage clickSignInButton() {
        $x(SIGN_IN_BUTTON_XPATH).click();
        return this;
    }

}
