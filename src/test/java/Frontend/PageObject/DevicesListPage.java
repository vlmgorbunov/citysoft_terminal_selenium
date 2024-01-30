package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DevicesListPage extends Abstract {

    public DevicesListPage (WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public DevicesListPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public By firstDeviceInList = By.xpath("//app-devices-page/app-devices/div/mat-table/mat-row[1]/mat-cell[8]/app-ui-button/button");

    public By cancelBtn = By.xpath(
            "//*[contains(@iconclassname, 'icon_cancel')]");

    public By devicesListModal = By.xpath(
            "/html/body/app-ui-modal/div");
}
