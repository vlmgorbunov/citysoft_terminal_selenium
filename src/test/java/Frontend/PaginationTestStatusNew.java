package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Проверка Пагинации на Очереди Видео status NEW")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaginationTestStatusNew extends Abstract {

    File file = new File("./src/test/resources/paginationTestStatusNew");

    BufferedWriter bw = new BufferedWriter(new FileWriter(file));

    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    public PaginationTestStatusNew() throws IOException {
    }

    @Test
    @DisplayName("ProcessingQueue pagination test status NEW")
    public void paginationTestStatusNew() throws InterruptedException, IOException, ParseException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        WaitElement(profilePage.terminalBtn);
        WaitAndClickToElement(profilePage.terminalBtn);

        WaitElement(terminalPage.processingQueueBtn);
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

        //pagination
        List<WebElement> pagination = driver.findElements(By.xpath("/html/body/app-root/div/div/div/main/app-processing-queue-page/app-ui-pagination/div/div"));
        int paginationSize = pagination.size();
        System.out.println("paginationSize " + paginationSize);

        for (int j = 1; j < paginationSize; j++) {

            try {

                List rows = driver.findElements(By.xpath("//app-processing-queue-page/app-processing-queue/div/mat-table/mat-row"));

                WebElement baseTable = driver.findElement(By.cssSelector("app-processing-queue-page > app-processing-queue > div > mat-table"));
                for (int m = 2; m <= rows.size(); m++) {
                    //To find rows in table
                    WebElement tableRowCreationDate = baseTable.findElement(By.cssSelector("app-processing-queue > div > mat-table > mat-row:nth-child(" + m + ") > mat-cell.mat-cell.cdk-cell.cdk-column-creationDate.mat-column-creationDate"));
                    String rowCreationDate = tableRowCreationDate.getText();
                    System.out.println("CreationDate in Table : " + rowCreationDate);
                    WebElement tableRowDeviceID = baseTable.findElement(By.cssSelector("app-processing-queue > div > mat-table > mat-row:nth-child(" + m + ") > mat-cell.mat-cell.cdk-cell.cdk-column-deviceId.mat-column-deviceId"));
                    String rowDeviceID = tableRowDeviceID.getText();
                    System.out.println("Device id : " + rowDeviceID);
                    WebElement tableRowSessionId = baseTable.findElement(By.cssSelector("app-processing-queue > div > mat-table > mat-row:nth-child(" + m + ") > mat-cell.mat-cell.cdk-cell.cdk-column-sessionId.mat-column-sessionId"));
                    String rowSessionID = tableRowSessionId.getText();
                    System.out.println("SessionId in Table : " + rowSessionID);
                    WebElement tableRowPocessingStatus = baseTable.findElement(By.cssSelector("app-processing-queue > div > mat-table > mat-row:nth-child(" + m + ") > mat-cell.mat-cell.cdk-cell.cdk-column-processingStatus.mat-column-processingStatus"));
                    String rowPocessingStatus = tableRowPocessingStatus.getText();
                    System.out.println("processingStatus in Table : " + rowPocessingStatus);
                    Thread.sleep(3000);
                    List rowsSecondPage = driver.findElements(By.xpath("//app-processing-queue-page/app-processing-queue/div/mat-table/mat-row"));


                    for (int i = 0; i < rowsSecondPage.size(); i++) {

                        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);


                        driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).isDisplayed();
                        log.info("video_link_" + i + " " + driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href"));
                        Assertions.assertNotNull((driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href")));
                        driver.get((driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")).getAttribute("href")));
                        Thread.sleep(16000);
                        log.info("Дата и время создания: " + GetText(processingQueuePage.dateAndTimeVideoCreated));
                        log.info("ГРЗ/ID устройства: " + GetText(processingQueuePage.grzAndIDdevice));
                        log.info("ID сессии: " + GetText(processingQueuePage.sessionId));
                        log.info("Статус: " + GetText(processingQueuePage.statusVideo));
                        log.info("Центр съемки: " + GetText(processingQueuePage.focusOnVideo));
                        bw.write("Центр съемки: " + GetText(processingQueuePage.focusOnVideo));
                        System.out.println(driver.findElement(By.cssSelector("app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(6) > td")).getCssValue("color"));
                        Assertions.assertEquals("rgba(0, 0, 0, 1)", driver.findElement(By.cssSelector("app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(6) > td")).getCssValue("color"));
                        log.info("Save button Enabled is " + iSElementEnabled((processingQueuePage.saveVideoBtn)));
                        Assertions.assertTrue(iSElementEnabled((processingQueuePage.saveVideoBtn)));

                        log.info("SaveAndContinue button Enabled is " + iSElementEnabled((processingQueuePage.saveVideoAndContinueBtn)));
                        Assertions.assertTrue(iSElementEnabled((processingQueuePage.saveVideoAndContinueBtn)));

                        Assertions.assertEquals(GetText(processingQueuePage.statusVideoHeaderNew), GetText(processingQueuePage.statusVideo));
                        Assertions.assertTrue(iSElementEnabled((processingQueuePage.saveVideoBtn)));
                        log.info("Make video As invalid Enabled is " + iSElementEnabled((processingQueuePage.makeVideoInvalidBtn)));


                        driver.navigate().back();

                    }

                    System.out.println("Total items on page " + j + ": " + rowsSecondPage.size());


                    if (driver.findElement(By.xpath(
                            "//*[contains(@id, 'next_page')]")).isDisplayed()) {
                        WaitElement(processingQueuePage.nextPage);
                        WaitAndClickToElement(processingQueuePage.nextPage);
                    }
                }
            } catch (NoSuchElementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.info(("Next btn not found! Last pagination page"));
            }
        }
    }
}







