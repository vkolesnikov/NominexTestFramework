package org.nominex.frontend.steps;

import io.qameta.allure.Step;
import org.nominex.frontend.pages.SignInPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInPageSteps {

    @Autowired
    private SignInPage signInPage;

    @Step("Filling the login field with a value: {0}")
    public void fillLogin(String login) {
        signInPage.fillLoginField(login);
    }

    @Step("Filling the password field with a value: {0}")
    public void fillPassword(String password) {
        signInPage.fillPasswordField(password);
    }

    @Step("Clicking on \"Sign in\" button")
    public void clickSignInButton() {
        signInPage.clickSignInButton();
    }

    @Step("Logging into account")
    public void login(String login, String password) {
        fillLogin(login);
        fillPassword(password);
        clickSignInButton();
    }

}
