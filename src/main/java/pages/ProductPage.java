package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class ProductPage {
    private SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public ProductPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By men = By.linkText("Men");
    private final By menTops = By.linkText("Tops");
    private final By menJackets = By.linkText("Jackets");
    private final By montanaProduct = By.xpath("//a[@class='product-item-link'][normalize-space()='Montana Wind Jacket']");
    private final By size = By.id("option-label-size-143-item-166");
    private final By color = By.id("option-label-color-93-item-49");
    private final By addToCartButton = By.id("product-addtocart-button");
    private final By addedMsg = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private final By sizeMsg = By.id("super_attribute[143]-error");
    private final By colorMsg = By.id("super_attribute[93]-error");

    public void hoverMenJackets(){
        windowManager.hover(men);
        windowManager.hover(menTops);
        windowManager.click(menJackets);
    }

    public void selectMontanaProduct(){
        windowManager.click(montanaProduct);
    }

    public void selectSize(){
        windowManager.click(size);
    }

    public void selectColor(){
        windowManager.click(color);
    }

    public String verifyItemAddedToCart(){
        return windowManager.verifyText(addedMsg);
    }

    public String verifySizeRequiredMsg(){
        return windowManager.verifyText(sizeMsg);
    }

    public String verifyColorRequiredMsg(){
        return windowManager.verifyText(colorMsg);
    }

    public boolean verifyProductIsDisplayed(){
        return driver.element().isElementDisplayed(montanaProduct);
    }

}
