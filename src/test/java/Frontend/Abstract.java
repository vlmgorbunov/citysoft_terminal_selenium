package Frontend;

import Frontend.PageObject.ProfilePage;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j2
abstract public class Abstract {
    public static EventFiringWebDriver driver;
    //public static RemoteWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static FirefoxOptions foxOptions;
    public static WebDriverWait wait;
    public static WebDriverWait waitTime;
    public static WebDriverWait waitTime2;
    public static Actions actions;
    public Properties props;

    public static String Browser = System.getProperty("browser");

    public static void setUp() {
    //    Если браузер не передаем через командную строку, по умолчанию Chrome
          Browser = "Chrome";
        if (Browser.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            //chromeOptions.setHeadless(true);
            //chromeOptions.addArguments("window-size=1920, 1080");
            driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        }
        if (Browser.contains("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            foxOptions = new FirefoxOptions();
            //foxOptions.setHeadless(true);
            //foxOptions.addArguments("window-size=1920, 1080");
            driver = new EventFiringWebDriver(new FirefoxDriver(foxOptions));
        }
        //driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), foxOptions);
        driver.manage().window().maximize();
        driver.register(new Custom());
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);

    //    RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//    final DesiredCapabilities browser = DesiredCapabilities.chrome();
//        browser.setCapability("enableVNC" , true);
//        WebDriverManager.chromedriver().setup();
 //       WebDriverManager.firefoxdriver().setup();
 //         chromeOptions = new ChromeOptions();
  //      WebDriverManager.operadriver().setup();
 //       firefoxOptions = new FirefoxOptions();
//        chromeOptions.setHeadless(true);
  //      firefoxOptions.addArguments("window-size=1920, 1080");
 //       chromeOptions.addArguments("window-size=1920, 1080");
//               chromeOptions.addArguments("window-size=1920, 1080");
//               chromeOptions.setCapability("enableVNC" , true);
   //     chromeOptions.addArguments("--headless=new");
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), browser);
//          driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
 //       driver = new FirefoxDriver(new FirefoxOptions(firefoxOptions));
 //       driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
 //         driver.register(new Custom());
 //      driver.manage().window().maximize();
  //      driver.register(new Custom());
//----        wait = new WebDriverWait(driver, 20);
 //       actions = new Actions(driver);
        //logs
//        LoggingPreferences loggingPreferences = new LoggingPreferences();
//        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
//        loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(chromeOptions);
//        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
//        chromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
    }

    @Rule
    public FailureTestWatcher testWatcher = new FailureTestWatcher();

    @BeforeEach
    public void init() throws IOException {
        setUp();
        FileInputStream in = new FileInputStream("src/test/resources/my.properties");
        props = new Properties();
        props.load(in);
    }


    @Step("Wait for element loginfield")
    public void WaitElement(By locator) {
        wait.until(visibilityOfElementLocated(locator));
    }

    @Step("Wait and Click to Element")
    public void WaitAndClickToElement(By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    }

    @Step("Clear element Input")
    public void ClearInput(WebElement element) {
        element.clear();
    }

    @Step("Ввод текста под Shadow root")
    public static void inputWord(WebElement element, String word) {
        element.sendKeys((word));
    }

    @Step("Element Get Text")
    public String GetText (WebElement element) {
        return element.getText();
    }

    @Step("Is Element Selected")
    public boolean iSElementSelected (By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        return element.isSelected();
    }

    @Step("Is Element Displayed")
    public boolean iSElementEnabled (By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        return element.isEnabled();
    }

    @Step("Проверка появится ли элемент {0}, за время - {1}")
    public boolean IfWaitElement(By locator, Integer time) {
        waitTime = new WebDriverWait(driver, time);
        try {
            waitTime.until(visibilityOfElementLocated(locator));
            log.info("Элемент обнаружен на странице " + locator);
            return true;
        } catch (TimeoutException e) {
            log.info("Элемент отсутсвует на странице " + locator);
            return false;
        }
    }

    @Step("Ожидание появления элемента {0}, за время - {1}")
    public void WaitElementTime(By locator, Integer time) {
        waitTime = new WebDriverWait(driver, time);
        waitTime.until(visibilityOfElementLocated(locator));
    }

    public boolean iSElementExists (By locator) {
        try {
            WaitElement(locator);
            WebElement element = driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Is Focus on video not null")
    public boolean iSFocusOnVideoNotNull (By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        return element.isEnabled();
    }

    @AfterEach
    @DisplayName("LogoutTest from devcs")
    public void logout() throws InterruptedException {
        ProfilePage profilePage;
        profilePage = new ProfilePage(driver);
        profilePage.userLogout();
    }

    public void takeScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("build/screenshot-" + fileName + ".jpg");
            Files.copy(scrFile.toPath(), destination.toPath(), REPLACE_EXISTING);

            System.out.println("=================== SCREENSHOT ========================");
            System.out.println("Saved to: " + destination.getAbsolutePath());
            System.out.println("=======================================================");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logBrowserConsoleLogs() {
        System.out.println("================== BROWSER LOGS =======================");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        System.out.println("=======================================================");
    }

    public class FailureTestWatcher extends TestWatcher {
        protected void failed(Throwable e, Description description) {
            // Make the filename safe to write to disk
            String testName = description.getMethodName();
            String safeFileName = testName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            takeScreenshot(safeFileName);

            logBrowserConsoleLogs();
        }
    }

    public final Supplier<ConditionFactory> WAITER = () -> Awaitility.given()
            .ignoreExceptions()
            .pollInterval(3, TimeUnit.SECONDS)
            .await()
            .dontCatchUncaughtExceptions()
            .atMost(10, TimeUnit.SECONDS);

    public boolean waitLogs(String expectedMessage){
        WebDriver driver = WebDriverRunner.getWebDriver();
                WebDriverManager.chromiumdriver().getWebDriver();
        AtomicBoolean isLogContains = new AtomicBoolean(false);

        WAITER.get().until(() -> {
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            printLogs(logEntries);
            isLogContains.set(logEntries.getAll().stream().anyMatch(x->x.getMessage().contains(expectedMessage)));
            return isLogContains.get();
        });
        return isLogContains.get();
    }

    public void printLogs(LogEntries logEntries)
    {
        for(LogEntry entry : logEntries){
            System.out.println(entry.getMessage());
    }
    }

    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

    }

    }
