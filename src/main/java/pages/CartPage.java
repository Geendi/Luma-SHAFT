package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class CartPage {
    private final SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public CartPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By cart = By.cssSelector("a.action.showcart");
    private final By cart2 = By.linkText("View and Edit Cart");
    private final By cartPopup = By.linkText("shopping cart");
    private final By itemInCart = By.xpath("//td[@class='col item']//strong[@class='product-item-name']");
    private final By emptyCartMsg = By.xpath("//p[normalize-space()='You have no items in your shopping cart.']");
    private final By quantity = By.cssSelector("input.input-text.qty");
    private final By deleteButton = By.cssSelector("a.action.action-delete");
    private final By updateCartButton = By.cssSelector("button.action.update");
    private final By addToCartButton = By.id("product-addtocart-button");

    public void addToCart(){
        windowManager.click(addToCartButton);
    }

    public void openCart(){
        windowManager.click(cart);
        windowManager.click(cart2);
    }

    public void openCartViaPopup(){
        windowManager.click(cartPopup);
    }

    public String verifyItemInCart(){
        return windowManager.verifyText(itemInCart);
    }

    public String verifyEmptyCart(){
        return windowManager.verifyText(emptyCartMsg);
    }

    public void changeQuantity(String quan){
        windowManager.type(quantity, quan);
    }

    public String verifyQuantity(){
        return windowManager.verifyText(quantity);
    }

    public void deleteItem(){
        windowManager.click(deleteButton);
    }

    public void updateCart(){
        windowManager.click(updateCartButton);
    }
}
