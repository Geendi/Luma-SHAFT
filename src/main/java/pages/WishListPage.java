package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class WishListPage {
    private final SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public WishListPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By accountDropdown = By.cssSelector("div[class='panel header'] button[type='button']");
    private final By wishList = By.partialLinkText("My Wish List");
    private final By addToWishListButton = By.cssSelector("div[class='product-addto-links'] a[class='action towishlist']");
    private final By deleteButton = By.xpath("//a[@title='Remove This Item']");
    private final By emptyList = By.cssSelector("div.message.info.empty");

    public void openWishList(){
        driver.element().clear(accountDropdown).click(wishList);
    }

    public void addToWishList(){
        windowManager.click(addToWishListButton);
    }

    public void deleteItem(){
        driver.element().hover(deleteButton).click(deleteButton);
    }

    public String verifyEmptyWishList(){
        return windowManager.verifyText(emptyList);
    }

    public boolean verifyItemDisplayed(){
        return driver.element().isElementDisplayed(deleteButton);
    }
}
