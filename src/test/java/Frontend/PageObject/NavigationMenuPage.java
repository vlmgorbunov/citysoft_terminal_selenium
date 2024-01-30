package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class NavigationMenuPage extends Abstract {

    public NavigationMenuPage(WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public  NavigationMenuPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public By processingQueueNavigationMenuBtn = By.xpath(
            "/html/body/app-root/div/div/app-navigation/nav/a[2]");

}

