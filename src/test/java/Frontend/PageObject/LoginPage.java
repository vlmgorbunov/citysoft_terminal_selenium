package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage extends Abstract {

    public LoginPage(WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public LoginPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }

//    public LoginPage (WebDriver driver) {
//        this.driver = (FirefoxDriver) driver;
//
//    }


    /**
     * Главаная страница - loginPage
     */
    public By loginField = By.xpath(
            "//*[contains(@id, 'username')]");

    /**
     * LoginPage - локаторы для авторизации
     */
    public By passwordField = By.xpath(
            "//*[contains(@id, 'password')]");

    public By signInBtn = By.xpath(
            "//*[contains(@id, 'kc-login')]");

}