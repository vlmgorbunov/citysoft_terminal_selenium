package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DeviceMonitoringPage extends Abstract {

    public DeviceMonitoringPage (WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public DeviceMonitoringPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public By descriptionFilter = By.xpath(
            "//*[contains(@formcontrolname, 'description')]");

    @FindBy(xpath = "//*[contains(@formcontrolname, 'description')]")
    public WebElement descriptionFilter1;

}
