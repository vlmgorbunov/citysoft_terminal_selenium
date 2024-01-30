package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
@Log4j2
@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Получить список ссылок с видео")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetVideoLinksTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Step
    public void getVideoLinks(int pageSize) throws IOException, InterruptedException {

        File file = new File("./src/test/resources/VideoLinks");

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        int count = 0;

        for (int i = 0; i < pageSize; i++) {
            driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).isDisplayed();
            log.info("video_link_" + i + " " + driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href"));
            Assertions.assertNotNull((driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href")));
            count++;
            bw.write(("video_link_" + i + " " + driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href") + count + "\r\n"));
            driver.get((driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href")));
            Thread.sleep(10000);
            log.info("Дата и время создания: "+GetText(processingQueuePage.dateAndTimeVideoCreated));
            log.info("ГРЗ/ID устройства: "+GetText(processingQueuePage.grzAndIDdevice));
            log.info("ID сессии: "+GetText(processingQueuePage.sessionId));
            log.info("Статус: "+GetText(processingQueuePage.statusVideo));
            log.info("Центр съемки: "+GetText(processingQueuePage.focusOnVideo));
            driver.navigate().back();
        }
        bw.close();
    }

    @Test
    @DisplayName("GetVideoLinksTest")
    public void sendVideoToProcessing() throws InterruptedException, IOException, ParseException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        int pageSize = 3;
        System.out.println("Page size is set to: "+pageSize);

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

        getVideoLinks(pageSize);
        }
    }
