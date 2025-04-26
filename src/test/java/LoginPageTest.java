import org.junit.jupiter.api.*;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.refresh;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTest extends BaseTest {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String INVALID_USERNAME = "wronguser";
    private static final String INVALID_PASSWORD = "wrongpass";

    private LoginPage loginPage;

    @BeforeEach
    void setupPage() {
        loginPage = new LoginPage();
    }

    @Test
    @Order(1)
    @DisplayName("Verify login page elements are present")
    void verifyLoginPageElements() {
        loginPage.verifyLoginPageElements();
    }

    @Test
    @Order(2)
    @DisplayName("Successful login with valid credentials")
    void successfulLogin() {
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        loginPage.verifySuccessfulLogin(VALID_USERNAME);
    }

    @Test
    @Order(3)
    @DisplayName("Failed login with invalid credentials")
    void failedLogin() {
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        loginPage.verifyLoginError();
    }

    @Test
    @Order(4)
    @DisplayName("Empty username validation")
    void emptyUsernameValidation() {
        loginPage.login("", VALID_PASSWORD);
        loginPage.verifyEmptyUsernameError();
    }

    @Test
    @Order(5)
    @DisplayName("Empty password validation")
    void emptyPasswordValidation() {
        loginPage.login(VALID_USERNAME, "");
        loginPage.verifyEmptyPasswordError();
    }

    @Test
    @Order(6)
    @DisplayName("Successful logout")
    void successfulLogout() {
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        loginPage.verifySuccessfulLogin(VALID_USERNAME);
        
        loginPage.logout();
        loginPage.verifyLoginPageElements();
    }

    @Test
    @Order(7)
    @DisplayName("Verify persistence of login state")
    void loginStatePersistence() {
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        refresh();
        loginPage.verifySuccessfulLogin(VALID_USERNAME);
    }
} 