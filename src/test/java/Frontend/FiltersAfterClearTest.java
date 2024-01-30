package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.ProcessingQueuePage;
import Frontend.PageObject.ProfilePage;
import Frontend.PageObject.TerminalPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Ошибка #76424 [Terminal] Удаляются данные из фильтров при нажатии кнопки Очистка фильтра")
public class FiltersAfterClearTest extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Test
    @DisplayName("Проверка отображения результатов в фильтрах после Clean")
    public void  filtersAfterClearTest() throws InterruptedException {


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

        WaitAndClickToElement(processingQueuePage.searchByGRZorID);

        WaitAndClickToElement(processingQueuePage.result1ByGRZoriID);

        WaitAndClickToElement(processingQueuePage.result2ByGRZoriID);

        Thread.sleep(4000);

        WaitAndClickToElement(processingQueuePage.clearFilterBtn);

        WaitAndClickToElement(processingQueuePage.searchByGRZorID);

        WaitAndClickToElement(processingQueuePage.result1ByGRZoriID);

        WaitAndClickToElement(processingQueuePage.result2ByGRZoriID);

        Thread.sleep(4000);

        Assertions.assertTrue(iSElementEnabled(processingQueuePage.result1ByGRZoriID));
        Assertions.assertTrue(iSElementEnabled(processingQueuePage.result2ByGRZoriID));
    }
}