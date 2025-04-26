import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTest extends BaseTest {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String INVALID_USERNAME = "wronguser";
    private static final String INVALID_PASSWORD = "wrongpass";

    @Test
    @Order(1)
    @DisplayName("Verify login page elements are present")
    void verifyLoginPageElements() {
        $("[data-testid=login-container]").shouldBe(visible);
        $("[data-testid=login-title]").shouldHave(text("Welcome!"));
        $("[data-testid=welcome-message]").shouldHave(text("We're happy to see you!"));
        $("[data-testid=username-input]").shouldBe(visible);
        $("[data-testid=password-input]").shouldBe(visible);
        $("[data-testid=submit-button]").shouldBe(visible);
    }

    @Test
    @Order(2)
    @DisplayName("Successful login with valid credentials")
    void successfulLogin() {
        $("[data-testid=username-input]").setValue(VALID_USERNAME);
        $("[data-testid=password-input]").setValue(VALID_PASSWORD);
        $("[data-testid=submit-button]").click();

        // Verify success page is shown
        $("[data-testid=success-container]").shouldBe(visible);
        $("[data-testid=success-title]").shouldHave(text("Поздравляем!"));
        $("[data-testid=success-message]").shouldHave(text("Вы успешно авторизовались как " + VALID_USERNAME));
    }

    @Test
    @Order(3)
    @DisplayName("Failed login with invalid credentials")
    void failedLogin() {
        $("[data-testid=username-input]").setValue(INVALID_USERNAME);
        $("[data-testid=password-input]").setValue(INVALID_PASSWORD);
        $("[data-testid=submit-button]").click();

        // Verify error modal is shown
        $("[data-testid=error-modal]").shouldBe(visible);
        $(".modal-title").shouldHave(text("Ошибка авторизации"));
    }

    @Test
    @Order(4)
    @DisplayName("Empty username validation")
    void emptyUsernameValidation() {
        $("[data-testid=password-input]").setValue(VALID_PASSWORD);
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldBe(visible)
                .shouldHave(text("❌ Введите логин"));
        $("#username").shouldHave(cssClass("input-error"));
    }

    @Test
    @Order(5)
    @DisplayName("Empty password validation")
    void emptyPasswordValidation() {
        $("[data-testid=username-input]").setValue(VALID_USERNAME);
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldBe(visible)
                .shouldHave(text("❌ Введите пароль"));
        $("#password").shouldHave(cssClass("input-error"));
    }

    @Test
    @Order(6)
    @DisplayName("Successful logout")
    void successfulLogout() {
        // First login
        $("[data-testid=username-input]").setValue(VALID_USERNAME);
        $("[data-testid=password-input]").setValue(VALID_PASSWORD);
        $("[data-testid=submit-button]").click();

        // Verify successful login
        $("[data-testid=success-container]").shouldBe(visible);

        // Perform logout
        $("[data-testid=logout-button]").click();

        // Verify return to login page
        $("[data-testid=login-container]").shouldBe(visible);
        $("[data-testid=login-title]").shouldHave(text("Welcome!"));
    }

    @Test
    @Order(7)
    @DisplayName("Verify persistence of login state")
    void loginStatePersistence() {
        // Login first
        $("[data-testid=username-input]").setValue(VALID_USERNAME);
        $("[data-testid=password-input]").setValue(VALID_PASSWORD);
        $("[data-testid=submit-button]").click();

        // Refresh page
        refresh();

        // Verify still logged in
        $("[data-testid=success-container]").shouldBe(visible);
        $("[data-testid=success-message]").shouldHave(text("Вы успешно авторизовались как " + VALID_USERNAME));
    }
} 