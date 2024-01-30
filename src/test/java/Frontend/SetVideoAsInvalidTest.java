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
@Feature("Отметить видео как невалидное на Очереди Видео")
public class SetVideoAsInvalidTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Test
    @DisplayName("SetVideoAsInvalid")
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

        WaitElement(processingQueuePage.inputControlContainer);
        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitElement(processingQueuePage.lastWeekField);
        WaitAndClickToElement(processingQueuePage.lastWeekField);

        WaitElement(processingQueuePage.ngxDropDownStatusBtn);
        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        //Выставляем новые

        WaitElement(processingQueuePage.newVideoStatus);
        WaitAndClickToElement(processingQueuePage.newVideoStatus);

        WaitElement(processingQueuePage.applyFilter);
        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        WaitAndClickToElement(processingQueuePage.checkbox0);

        WaitAndClickToElement(processingQueuePage.setVideoAsInvalid);

        Thread.sleep(4000);

        String firstVideoStatus = GetText(processingQueuePage.firstVideoStatus);

        Assertions.assertEquals(firstVideoStatus, "Испорчено", "Statuses is equals");

    }
}