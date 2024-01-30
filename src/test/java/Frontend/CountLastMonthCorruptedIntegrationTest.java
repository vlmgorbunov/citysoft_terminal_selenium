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
@Feature("Интеграционный тест проверки количества испорченых записей на Очереди Видео за Месяц")
public class CountLastMonthCorruptedIntegrationTest extends Abstract {

    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;
    APITest apiTest = new APITest();

    DevTools devTools;

    @Test
    @DisplayName("Интеграционный тест проверки количетсва испорченых записей на Очереди Видео за Месяц")
    public void countLastMonthCorruptedIntegrationTest() throws InterruptedException, IOException, ParseException {


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

        WaitAndClickToElement(processingQueuePage.lastMonthField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

        //Выставляем невалидные
        WaitAndClickToElement(processingQueuePage.InvalidStatus2);

        WaitAndClickToElement(processingQueuePage.applyFilter);
        Thread.sleep(4000);

        //pagination
        List<WebElement> pagination = driver.findElements(By.xpath("/html/body/app-root/div/div/div/main/app-processing-queue-page/app-ui-pagination/div/div"));
        int paginationSize = pagination.size();
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


                    if (driver.findElement(By.xpath(
                            "//*[contains(@id, 'next_page')]")).isDisplayed()) {
                        WaitAndClickToElement(processingQueuePage.nextPage);
                    }
                }
            } catch (NoSuchElementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.info(("Next btn not found! Last pagination page"));
            }
            int totalElementsCount = totalElements.size();
            log.info("Total items : " + totalElementsCount);

            log.info("API POST /video/count last month CORRUPTED Response :" + apiTest.postCountCorruptedLastMonth());

            Assertions.assertEquals(apiTest.postCountCorruptedLastMonth(), String.valueOf(totalElementsCount), "Count of elements is equals");

        }
    }
}


