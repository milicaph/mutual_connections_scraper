package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Parameters;
import utilities.SelUtils;

public class Login {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(id = "username")
    private WebElement username;
    @FindBy (id = "password")
    private WebElement password;
    @FindBy (css = "button[type=submit]")
    private WebElement loginBtn;


    public Login(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void loginToLinkedin(){
        driver.get(Parameters.url);
        try { Thread.sleep(5000); } catch(Exception ignored){}
        SelUtils.waitAndSendKeys(username, Parameters.username, wait);
        SelUtils.waitAndSendKeys(password, Parameters.password, wait);
        SelUtils.waitToClickAndClick(loginBtn, wait);
        try { Thread.sleep(5000); } catch(Exception ignored){}

    }






}
