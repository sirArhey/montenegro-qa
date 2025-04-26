package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    // Page elements
    private final SelenideElement loginContainer = $("[data-testid=login-container]");
    private final SelenideElement loginTitle = $("[data-testid=login-title]");
    private final SelenideElement welcomeMessage = $("[data-testid=welcome-message]");
    private final SelenideElement usernameInput = $("[data-testid=username-input]");
    private final SelenideElement passwordInput = $("[data-testid=password-input]");
    private final SelenideElement submitButton = $("[data-testid=submit-button]");
    private final SelenideElement errorModal = $("[data-testid=error-modal]");
    private final SelenideElement errorMessage = $("[data-testid=error-message]");
    private final SelenideElement successContainer = $("[data-testid=success-container]");
    private final SelenideElement successTitle = $("[data-testid=success-title]");
    private final SelenideElement successMessage = $("[data-testid=success-message]");
    private final SelenideElement logoutButton = $("[data-testid=logout-button]");

    // Page actions
    public void login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        submitButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    // Verification methods
    public void verifyLoginPageElements() {
        loginContainer.shouldBe(visible);
        loginTitle.shouldHave(text("Welcome!"));
        welcomeMessage.shouldHave(text("We're happy to see you!"));
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        submitButton.shouldBe(visible);
    }

    public void verifySuccessfulLogin(String username) {
        successContainer.shouldBe(visible);
        successTitle.shouldHave(text("Поздравляем!"));
        successMessage.shouldHave(text("Вы успешно авторизовались как " + username));
    }

    public void verifyLoginError() {
        errorModal.shouldBe(visible);
        $(".modal-title").shouldHave(text("Ошибка авторизации"));
    }

    public void verifyEmptyUsernameError() {
        errorMessage.shouldBe(visible)
                .shouldHave(text("❌ Введите логин"));
        $("#username").shouldHave(cssClass("input-error"));
    }

    public void verifyEmptyPasswordError() {
        errorMessage.shouldBe(visible)
                .shouldHave(text("❌ Введите пароль"));
        $("#password").shouldHave(cssClass("input-error"));
    }
} 