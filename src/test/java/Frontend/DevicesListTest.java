package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.DevicesListPage;
import Frontend.PageObject.NavigationMenuPage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

//@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Ошибка #76475 [Terminal] не открывается окно Настройки устройства после перехода на другие страницы")
public class DevicesListTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    DevicesListPage devicesListPage;

    NavigationMenuPage navigationMenuPage;

    @Test
    @DisplayName("DevicesListTest")
    public void setVideoAsInvalidTest() throws InterruptedException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        devicesListPage = new DevicesListPage(driver);
        navigationMenuPage = new NavigationMenuPage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.devicesListBtn);

        Thread.sleep(2000);

        //set filter last month

        WaitAndClickToElement(devicesListPage.firstDeviceInList);

        Thread.sleep(2000);

        WaitAndClickToElement(devicesListPage.devicesListModal);

        WaitElement(devicesListPage.cancelBtn);
        WaitAndClickToElement(devicesListPage.cancelBtn);

        Thread.sleep(2000);

        driver.navigate().back();

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        driver.navigate().back();

        WaitAndClickToElement(terminalPage.devicesListBtn);

        WaitAndClickToElement(devicesListPage.firstDeviceInList);

        Thread.sleep(2000);

        WaitAndClickToElement(devicesListPage.devicesListModal);

        WaitAndClickToElement(devicesListPage.cancelBtn);
    }
}