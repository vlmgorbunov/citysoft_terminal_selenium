package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Epic("Проверка статуса видео при фильтрации")
@Feature("Проверка статуса видео при фильтрации")
public class CheckVideoStatus extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    public void checkVideoStatus(int pageSize) {
        for (int i = 2; i <= pageSize; i++) {
            driver.findElement(By.cssSelector("mat-table > mat-row:nth-child("+i+") > mat-cell.mat-cell.cdk-cell.cdk-column-processingStatus.mat-column-processingStatus")).getText();
        }
    }


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

        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastMonthField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        //Проверяем новые

        WaitAndClickToElement(processingQueuePage.newVideoStatus);

        ClearInput(driver.findElement(processingQueuePage.pageSizeInput));
        inputWord(driver.findElement(processingQueuePage.pageSizeInput), String.valueOf(pageSize));

        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        String newVideoStatus = GetText(processingQueuePage.firstVideoStatus);

        Assertions.assertEquals(newVideoStatus, "Новые", "Statuses is equals");

        Thread.sleep(4000);

        //Проверяем в обработке

        WaitAndClickToElement(processingQueuePage.clearFilterBtn);

        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastMonthField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        WaitAndClickToElement(processingQueuePage.mlFinishedVideoStatus);

        ClearInput(driver.findElement(processingQueuePage.pageSizeInput));
        inputWord(driver.findElement(processingQueuePage.pageSizeInput), String.valueOf(pageSize));

        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        String secondVideoStatus = GetText(processingQueuePage.firstVideoStatus);

        Assertions.assertEquals(secondVideoStatus, "Завершена обработка ML", "Statuses is equals");

        //Проверяяем испорчено

        WaitAndClickToElement(processingQueuePage.clearFilterBtn);

        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastMonthField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        WaitAndClickToElement(processingQueuePage.InvalidVideoStatus);

        ClearInput(driver.findElement(processingQueuePage.pageSizeInput));
        inputWord(driver.findElement(processingQueuePage.pageSizeInput), String.valueOf(pageSize));

        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        String thirdVideoStatus = GetText(processingQueuePage.firstVideoStatus);
        Assertions.assertEquals(thirdVideoStatus, "Испорчено", "Statuses is equals");
    }
}