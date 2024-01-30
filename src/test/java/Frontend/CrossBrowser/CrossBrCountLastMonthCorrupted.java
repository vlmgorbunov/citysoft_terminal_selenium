package Frontend.CrossBrowser;

import Frontend.ConfProperties;
import Frontend.LoginTest;
import Frontend.PageObject.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import static Frontend.CrossBrowser.CrossBrowser.driver;

@ExtendWith(TestListenerCrossBrowser.class)
public class CrossBrCountLastMonthCorrupted {

    LoginPage loginPage;
    CrossBrowser crossBrowser;

    @DisplayName("Кроссбраузерность")
    @ParameterizedTest
    @CsvSource({"Chrome", "FireFox"})
    public void Browser (String value) throws IOException {
        loginPage = new LoginPage(driver);
        crossBrowser = new CrossBrowser();

        crossBrowser.setUp(value);
        driver.get(ConfProperties.getProperty("devcs"));

        System.out.println(value);
    }
}