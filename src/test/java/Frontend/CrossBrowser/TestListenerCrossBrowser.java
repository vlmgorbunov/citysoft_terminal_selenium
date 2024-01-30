package Frontend.CrossBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import static Frontend.CrossBrowser.CrossBrowser.Browser;
import static Frontend.CrossBrowser.CrossBrowser.driver;

public class TestListenerCrossBrowser implements TestWatcher{

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (Browser.contains("Chrome")) {
            Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Логи после падения теста: ",
                    String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
            driver.quit();
        } else {
            WebDriverManager.firefoxdriver().quit();
            driver.quit();
        }
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        if (Browser.contains("Chrome")) {
            Allure.getLifecycle().addAttachment("Скриншот после успешного прохождения теста", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Логи после успешного прохождения теста: ",
                    String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
            driver.quit();
        } else {
            WebDriverManager.firefoxdriver().quit();
            driver.quit();
        }
    }
}