package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SelUtils;

import java.util.List;

public class Profile {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "a[data-control-name=url_card_action_click_highlights_shared_connections]")
    private List<WebElement> mutualConnections;

    public Profile(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
    public void openProfileUrl(String profileURL){
        driver.get(profileURL);


    }
    public void openMutualConnections(){
        SelUtils.waitGetFromListClick(mutualConnections, 0, wait);


    }


}
