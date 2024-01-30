package Frontend.PageObject;

import Frontend.Abstract;
import groovyjarjarantlr4.v4.tool.ast.TerminalAST;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TerminalPage extends Abstract {
//
//    public TerminalPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public TerminalPage(WebDriver driver) {
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public TerminalPage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public By processingQueueBtn = By.cssSelector("app-home > div > a:nth-child(1)");



    public By devicesListBtn = By.cssSelector("app-home > div > a:nth-child(3)");

    public By deviceMonitoringBtn = By.cssSelector("app-home > div > a:nth-child(6)");


}
