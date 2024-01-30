package Frontend;

import API.APITest;
import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Интеграционный тест проверки количества записей на Очереди Видео за Вчера")
public class CountYesterdayIntegrationTest extends Abstract {

    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;
    APITest apiTest = new APITest();

    DevTools devTools;

    @Test
    @DisplayName("Интеграционный тест проверки проверки количетсва записей на Очереди Видео за Вчера")
    public void countYesterdayIntegrationTest() throws InterruptedException, IOException, ParseException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

//        boolean isLogsExists = waitLogs(authMessage);
//        Assertions.assertTrue(isLogsExists, "Cообщение в логах " + authMessage + "не найдено");

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        Thread.sleep(4000);

        //set filter last month
        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.yesterdayField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        WaitAndClickToElement(processingQueuePage.applyFilter);
        Thread.sleep(4000);

        //pagination
        if (IfWaitElement(processingQueuePage.paginationSize, 10)) {
            WebElement pagination = driver.findElement(By.xpath("//div[@class='page_numbers']/div[last()]"));
            int paginationSize = Integer.parseInt(pagination.getText());


            log.info("paginationSize " + paginationSize);


            List<String> elements = new ArrayList<String>();


            for (int j = 1; j <= paginationSize; j++) {

                List<String> totalElements = null;
                try {

                    totalElements = new ArrayList<String>();
                    List<WebElement> rows = driver.findElements(By.xpath("//app-processing-queue-page/app-processing-queue/div/mat-table/mat-row"));
                    for (int m = 2; m <= rows.size(); m++) {
                        //To find rows in table
                        for (WebElement totalElement : rows) {
                            elements.add(String.valueOf(totalElement));
                            Thread.sleep(2000);
                        }

                        List<WebElement> rowsNextPage = driver.findElements(By.xpath("//app-processing-queue-page/app-processing-queue/div/mat-table/mat-row"));

                        for (WebElement totalElement1 : rowsNextPage) {
                            totalElements.add(String.valueOf(totalElement1));
                            Thread.sleep(2000);
                        }

                        log.info("Total items on page " + j + ": " + rowsNextPage.size());


                        if (IfWaitElement(processingQueuePage.nextPage, 10)) {
                            WaitAndClickToElement(processingQueuePage.nextPage);
                        }
                    }
                } catch (NoSuchElementException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    log.info(("Next btn not found! Last pagination page"));
                }
                Integer totalElementsCount = totalElements.size();
                log.info("Total items : " + totalElementsCount);

                log.info("API POST /video/count yesterday Response :" + apiTest.postCountYesterday());
                Assertions.assertEquals(apiTest.postCountYesterday(), String.valueOf(totalElementsCount), "Count of elements is equals");

            }
        }
    }
}


