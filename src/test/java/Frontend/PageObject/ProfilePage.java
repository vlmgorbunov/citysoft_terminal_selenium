package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProfilePage extends Abstract {

//    public ProfilePage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }

    public ProfilePage(WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;

    }

//    public ProfilePage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }

    public By userPushBtn = By.cssSelector(".user_push");

    public By logoutBtn = By.cssSelector(".user_container > app-ui-button > button");

    public By terminalBtn = By.xpath(
            "//*[contains(@id, 'terminal')]");


    public void userLogout() throws InterruptedException {
        ProfilePage profilePage;
        profilePage = new ProfilePage(driver);
        WaitAndClickToElement(profilePage.userPushBtn);
        WaitAndClickToElement(profilePage.logoutBtn);

    }
}
