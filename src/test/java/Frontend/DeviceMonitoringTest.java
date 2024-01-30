package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.DeviceMonitoringPage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Задача #77841[Terminal-frontend] Убрать на страницу Мониторинг устройств из фильтра по умолчанию Автобус")
public class DeviceMonitoringTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    DeviceMonitoringPage deviceMonitoringPage;

    @Test
    @DisplayName("Убрать на страницу Мониторинг устройств из фильтра по умолчанию Автобус")
    public void  defaultFilterDeviceMonitoring() throws InterruptedException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        deviceMonitoringPage = new DeviceMonitoringPage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.deviceMonitoringBtn);

        Thread.sleep(4000);

        String defaultDescription = GetText(deviceMonitoringPage.descriptionFilter1);

        Assertions.assertEquals(defaultDescription, "", "Default filter is empty");
    }
    }
