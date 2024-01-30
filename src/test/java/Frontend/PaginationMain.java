package Frontend;

import API.TestListenerApi;
import API.Utils;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@Log4j2
@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Проверка Пагинации")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaginationMain extends Abstract {

    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Test
    @DisplayName("ProcessingQueue pagination test")
    public void paginationTest() throws InterruptedException, IOException, ParseException {


        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        Thread.sleep(4000);

        WaitAndClickToElement(processingQueuePage.selectContract);

        WaitAndClickToElement(processingQueuePage.selectTestContract);

        //set filter last month
        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.lastWeekField);

        WaitAndClickToElement(processingQueuePage.ngxDropDownStatusBtn);

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

                WebElement baseTable = driver.findElement(By.cssSelector("app-processing-queue-page > app-processing-queue > div > mat-table"));
                for (int m = 2; m <= rows.size(); m++) {
                    //To find rows in table
                    for (WebElement totalElement : rows) {
                        elements.add(String.valueOf(totalElement));
                        Thread.sleep(2000);
                    }
                    List<WebElement> rowsSecondPage = driver.findElements(By.xpath("//app-processing-queue-page/app-processing-queue/div/mat-table/mat-row"));

                    for (WebElement totalElement1 : rowsSecondPage) {
                        totalElements.add(String.valueOf(totalElement1));
                        Thread.sleep(2000);
                    }

                    log.info("Total items on page " + j + ": " + rowsSecondPage.size());


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
            int totalElementsCount = totalElements.size();
            log.info("Total items : " + totalElementsCount);

        //API запрос
        Utils utils = new Utils();

        String currentDate = utils.currentDateEndOFDayISO8601.toString();

        String lastWeek = utils.lastWeekISO8601.toString();

            String response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[\"1\"],\n" +
                        "    \"dateFrom\" : \"" + lastWeek + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingsStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post("https://devcs.avtodoria.ru/terminal/api/rest/preprocessing/video/count")
                .then().statusCode(200)
                .body(Matchers.anything())
                .extract().asString();
        log.info(response);
        Assertions.assertEquals(response, String.valueOf(totalElementsCount), "Count of elements is equals");
    }
}
}







