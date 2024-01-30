package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Проверка полей на детальной странице видее")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessingQueueCheckFieldsTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    public void checkVideoLink(int pageSize) {
        for (int i = 0; i < pageSize; i++) {
            driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).isDisplayed();
        }
    }

    @Test
    @DisplayName("processing Queue Check Fields Test")
    public void setVideoAsInvalidTest() throws InterruptedException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        int pageSize = 20;

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        Thread.sleep(4000);

        //set filter last month

        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastMonthField);

        ClearInput(driver.findElement(processingQueuePage.pageSizeInput));
        inputWord(driver.findElement(processingQueuePage.pageSizeInput), String.valueOf(pageSize));

        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        WaitAndClickToElement(processingQueuePage.checkbox0);

        WaitAndClickToElement(processingQueuePage.setVideoAsInvalid);

        String firstVideoStatus = GetText(processingQueuePage.firstVideoStatus);

        Assertions.assertEquals(firstVideoStatus, "Новые", "Statuses is equals");

    }
}