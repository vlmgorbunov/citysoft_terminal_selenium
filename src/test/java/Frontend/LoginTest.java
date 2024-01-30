package Frontend;

import API.TestListenerApi;
import Frontend.PageObject.LoginPage;
import Frontend.PageObject.ProfilePage;
import io.qameta.allure.Feature;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Feature("Авторизация на портале devcs")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends Abstract {
    LoginPage loginPage;
    ProfilePage profilePage;

    @Test
    @DisplayName("LoginTest on devcs")
    public void loginDevCSTest() throws InterruptedException {

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.get(ConfProperties.getProperty("devcs"));
        WaitElement(loginPage.loginField);
        inputWord(driver.findElement(loginPage.loginField), ConfProperties.getProperty("login"));
        WaitElement(loginPage.passwordField);
        inputWord(driver.findElement(loginPage.passwordField),  ConfProperties.getProperty("password"));

        WaitElement(loginPage.signInBtn);
        WaitAndClickToElement(loginPage.signInBtn);

        Thread.sleep(4000);
    }
}