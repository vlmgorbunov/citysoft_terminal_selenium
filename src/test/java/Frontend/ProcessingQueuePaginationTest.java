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
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Проверка Пагинации на Очереди Видео")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessingQueuePaginationTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Test
    @DisplayName("ProcessingQueue pagination test")
    public void sendVideoToProcessing() throws InterruptedException, IOException, ParseException {

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        Thread.sleep(4000);

        //set filter last month


        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastWeekField);

        WaitAndClickToElement(processingQueuePage.applyFilter);

        Thread.sleep(4000);

        //pagination

        List<WebElement> pagination = driver.findElements(By.xpath("/html/body/app-root/div/div/div/main/app-processing-queue-page/app-ui-pagination/div/div"));
        int paginationSize = pagination.size();
          System.out.println("paginationSize " + paginationSize);
//            int paginationSize = Integer.parseInt(driver.findElement(By.cssSelector("#last_page")).getText());
//            System.out.println("paginationSize " + paginationSize);

            //pagination
            for (int j = 1; j <= paginationSize; j++) {

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
                            try {


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


//                          String logError6628200 = "[SEVERE] https://devcs.avtodoria.ru/terminal/main.a42871b6046aa7dc.js 0:975763 'ERROR' 6628200";
//                          boolean isLogsExists = waitLogs(logError6628200 );
//                          Assertions.assertTrue(isLogsExists, "Cообщение в логах " + logError6628200  + "не найдено");

                        driver.navigate().back();


                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        log.info("Video not found "+ driver.findElement(By.xpath("//*[contains(@id, 'video_link_" + i + "')]")));
                    }
                }
                    System.out.println("Total items on page " + j + ": " + rowsSecondPage.size());

                    }
                System.out.println("Total items on page " + j + ": " + rows.size());
                WaitAndClickToElement(processingQueuePage.nextPage);

                    }
            }

        }




