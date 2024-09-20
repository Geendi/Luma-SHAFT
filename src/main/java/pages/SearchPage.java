package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchPage {
    private final SHAFT.GUI.WebDriver driver;
    protected WindowManager windowManager;

    public SearchPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
        this.windowManager = new WindowManager(driver);
    }

    private final By searchBox = By.id("search");
    private final By suggestionProduct= By.id("qs-option-2");

    public void searchProduct(String product){
        driver.element().type(searchBox, product).keyPress(searchBox, Keys.ENTER);
    }

    public void searchUsingAutoSuggestion(String suggest){
        windowManager.type(searchBox, suggest);
        windowManager.click(suggestionProduct);
    }

}
