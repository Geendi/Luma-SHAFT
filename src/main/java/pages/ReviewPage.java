package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ReviewPage {
    private SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;
    Actions actions;

    public ReviewPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
        actions = new Actions(driver.getDriver());
    }

    private final By accountDropdown = By.cssSelector("div[class='panel header'] button[type='button']");
    private final By nicknameField = By.id("nickname_field");
    private final By myReview = By.linkText("My Product Reviews");
    private final By addReviewButton = By.linkText("Add Your Review");
    private final By summaryField = By.id("summary_field");
    private final By reviewField = By.id("review_field");
    private final By submitButton = By.cssSelector("button.action.submit.primary");
    private final By rating5stars = By.cssSelector("label#Rating_5_label");
    private final By ratingErrors = By.id("ratings[4]-error");
    private final By nicknameErrors = By.id("nickname_field-error");
    private final By summaryErrors = By.id("summary_field-error");
    private final By reviewErrors = By.id("review_field-error");

    public void openReviewPage(){
        driver.element().click(accountDropdown).click(myReview);
    }

    public void setNickname(String nickname){
        windowManager.type(nicknameField, nickname);
    }

    public void addReview(){
        windowManager.click(addReviewButton);
    }

    public void setSummary(String summary){
        windowManager.type(summaryField, summary);
    }

    public void setReview(String review){
        windowManager.type(reviewField, review);
    }

    public void clickSubmit(){
        windowManager.click(submitButton);
    }

    public void rating(){
        actions.moveToElement(driver.getDriver().findElement(rating5stars)).click().perform();
    }

    public String verifyEmptyRating(){
        return windowManager.verifyText(ratingErrors);
    }

    public String verifyEmptyNickname(){
        return windowManager.verifyText(nicknameErrors);
    }

    public String verifyEmptySummary(){
        return windowManager.verifyText(summaryErrors);
    }

    public String verifyEmptyReview(){
        return windowManager.verifyText(reviewErrors);
    }

}
