package Frontend.PageObject;

import Frontend.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProcessingQueuePage extends Abstract {

    public ProcessingQueuePage(WebDriver driver) {
        ProcessingQueuePage.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public ProcessingQueuePage(WebDriver driver){
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }


    public By inputControlContainer = By.xpath("//*[contains(@id, 'date_picker_input')]");
    public By todayField = By.cssSelector(".md-drppicker li:nth-child(1) > button");
    public By yesterdayField = By.cssSelector(".md-drppicker li:nth-child(2) > button");
    public By lastWeekField = By.cssSelector(".md-drppicker li:nth-child(3) > button");
    public By lastMonthField = By.cssSelector(".md-drppicker li:nth-child(4) > button");
    public By applyDateRangePickerBtn = By.cssSelector(".btn");
    public By pageSizeInput = By.xpath("//*[contains(@formcontrolname, 'pageSize')]");

    public By searchByGRZorID = By.cssSelector("app-queue-filter > div > form > ngx-select-dropdown:nth-child(3) > div > button");

    public By result1ByGRZoriID = By.cssSelector("app-queue-filter > div > form > ngx-select-dropdown:nth-child(3) > div > div > ul.available-items > li:nth-child(1)");

    public By result2ByGRZoriID = By.cssSelector("app-queue-filter > div > form > ngx-select-dropdown:nth-child(3) > div > div > ul.available-items > li:nth-child(2)");

    public By applyFilter = By.cssSelector("app-processing-queue-page > app-queue-filter > div > form > app-ui-button:nth-child(7)");
    public By alertDismissable = By.cssSelector(".alert > alert-dismissable > alert-info");

    //  Выбранные 1 видео помечены как невалидные
    public By videoLink = By.xpath("//*[contains(@id, 'video_link_')]");

    public By checkbox0 = By.xpath(
            "//*[contains(@id, 'checkbox_0')]");

    public By setVideoAsInvalid = By.cssSelector("div.buttons_container > app-ui-button:nth-child(2) > button");

    public By sendVideoToProcessing = By.cssSelector("div.buttons_container > app-ui-button:nth-child(1) > button");

    @FindBy(css = "mat-table > mat-row:nth-child(2) > mat-cell.mat-cell.cdk-cell.cdk-column-processingStatus.mat-column-processingStatus")
    public WebElement firstVideoStatus;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(1) > td")
    public WebElement dateAndTimeVideoCreated;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(2) > td")
    public WebElement grzAndIDdevice;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(3) > td")
    public WebElement sessionId;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(4) > td")
    public WebElement statusVideo;
    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_info > table > tr:nth-child(6) > td")
    public WebElement focusOnVideo;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_block__video_status.corrupted")
    public WebElement statusVideoHeaderCorrupted;

    @FindBy(css = "app-video-preprocessing-page > app-homography-editor > div.canvas_container > div.video_block > div.video_block__video_status.new\n")
    public WebElement statusVideoHeaderNew;



    public By ngxDropDownStatusBtn = By.cssSelector("app-queue-filter > div > form > ngx-select-dropdown:nth-child(5) > div > button");

    public By newVideoStatus = By.cssSelector("ngx-select-dropdown:nth-child(5) > div > div > ul.available-items > li:nth-child(1)");
    public By mlProcessedVideoStatus = By.cssSelector("ngx-select-dropdown.text_input_select.ng-valid.ng-touched.ng-dirty > div > div > ul.available-items > li:nth-child(2)");

    public By selectContract = By.cssSelector("app-processing-queue-page > app-queue-filter > div > form > ngx-select-dropdown:nth-child(2) > div > button");

    public By selectTestContract = By.cssSelector("app-processing-queue-page > app-queue-filter > div > form > ngx-select-dropdown:nth-child(2) > div > div > ul.available-items > li:nth-child(1)");


    public By mlFinishedVideoStatus = By.cssSelector("ngx-select-dropdown.text_input_select.ng-valid.ng-touched.ng-dirty > div > div > ul.available-items > li:nth-child(3)");

    public By mlErrorVideoStatus = By.cssSelector("ngx-select-dropdown.text_input_select.ng-valid.ng-touched.ng-dirty > div > div > ul.available-items > li:nth-child(4)");

    public By InvalidVideoStatus = By.cssSelector("ngx-select-dropdown.text_input_select.ng-valid.ng-touched.ng-dirty > div > div > ul.available-items > li:nth-child(5)");

    public By InvalidStatus2 = By.xpath("/html/body/app-root/div/div/div/main/app-processing-queue-page/app-queue-filter/div/form/ngx-select-dropdown[3]/div/div/ul[2]/li[5]");
    public By nsDiconClose = By.cssSelector(".nsdicon-close");

    public By clearFilterBtn = By.xpath(
            "//*[contains(@id, 'clear_button')]");


    public By paginationCurrentPage = By.cssSelector("#current_page");

    public By nextPage = By.xpath(
            "//*[contains(@id, 'next_page')]");

    public By saveVideoBtn = By.cssSelector("app-homography-editor > div.canvas_container > div.foto_block > div.tools_block_bottom > app-ui-button:nth-child(1) > button");

    public By saveVideoAndContinueBtn = By.cssSelector("app-homography-editor > div.canvas_container > div.foto_block > div.tools_block_bottom > app-ui-button:nth-child(2) > button");

    public By makeVideoInvalidBtn = By.cssSelector("app-homography-editor > div.canvas_container > div.video_block > div.controls_container.wide_container.colored > div > div.right_buttons > app-ui-button:nth-child(3) > button");


    @FindBy(css = "#last_page")
    public WebElement paginationLastPage;

    public By paginationSize = By.xpath("/html/body/app-root/div/div/div/main/app-processing-queue-page/app-ui-pagination/div/div");


  //  public By paginationLastPage = By.cssSelector("#last_page");

    public By paginationPrevPage = By.cssSelector("#prev_page");

    public By paginationFirstPage = By.cssSelector("#first_page");
}
