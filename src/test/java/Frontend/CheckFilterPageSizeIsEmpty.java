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
@Feature("[FRONT] #Ошибка #77757 Проверка того, что сли pageSize пустой, то кнопка 'Отфильтровать' не кликабельна")
public class CheckFilterPageSizeIsEmpty extends Abstract {
    ProfilePage profilePage;
    TerminalPage terminalPage;
    ProcessingQueuePage processingQueuePage;

    @Test
    @DisplayName("FiltersTest PageSize is Empty")
    public void filtersTestPageSizeIsEmpty() throws InterruptedException {

        LoginTest loginTest = new LoginTest();
        loginTest.loginDevCSTest();

        profilePage = new ProfilePage(driver);
        terminalPage = new TerminalPage(driver);
        processingQueuePage = new ProcessingQueuePage(driver);

        WaitAndClickToElement(profilePage.terminalBtn);

        WaitAndClickToElement(terminalPage.processingQueueBtn);

        Thread.sleep(4000);

        WaitAndClickToElement(processingQueuePage.inputControlContainer);

        WaitAndClickToElement(processingQueuePage.todayField);

        ClearInput(driver.findElement(processingQueuePage.pageSizeInput));
        inputWord(driver.findElement(processingQueuePage.pageSizeInput), " ");

        Assertions.assertFalse(iSElementSelected(processingQueuePage.applyFilter));

    }
}