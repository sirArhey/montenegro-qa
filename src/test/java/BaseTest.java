import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        // Assuming the HTML file is served locally. Adjust the path accordingly
        Configuration.baseUrl = "file://" + System.getProperty("user.dir");
    }

    @BeforeEach
    void openLoginPage() {
        open("/application.html");
        // Clear localStorage before each test
        localStorage().clear();
        refresh();
    }
} 