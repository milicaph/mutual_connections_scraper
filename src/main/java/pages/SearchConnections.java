package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchConnections {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "button[aria-label*=Connections]")
    private List<WebElement> connectionFilters;
    @FindBy(id = "network-S")
    private WebElement checkboxScnd;
    @FindBy(id = "network-F")
    private WebElement checkboxFr;
    @FindBy(css = "button[aria-label*=Connections]")
    private List<WebElement> showButtons;
    @FindBy(css = "span.entity-result__title > div > span > span > a")
    private List<WebElement> connectionUrls;
    @FindBy(css = "span.entity-result__title > div > span > span > a > span > span:nth-child(1)")
    private List<WebElement> connectionNames;
    @FindBy(css = "button[class*=pagination__button--next]")
    private WebElement nextBtn;

    public SearchConnections(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void chooseSecondConnections(){
        SelUtils.focusAndClick(connectionFilters.get(1), driver, wait);
        String currentURL = driver.getCurrentUrl();
        String secondConnectionURL = currentURL + "&network=[\"F\"]";
        System.out.println("DEBUGGER" + secondConnectionURL);
        driver.get(secondConnectionURL);

    }

    public void fillProfileNamesAndUrls(ArrayList<String> urls, ArrayList<String> names) {
        wait.until(ExpectedConditions.visibilityOfAllElements(connectionUrls));

        String currentURL = driver.getCurrentUrl();

        for (int i = 1; i < 200; i++) {

            try { Thread.sleep(5000); } catch(Exception ignored){}
            if(!SelUtils.ifElementExists(driver, "span.entity-result__title > div > span > span > a")) {
                break;
            }

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.entity-result__title > div > span > span > a")));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.entity-result__title > div > span > span > a > span > span:nth-child(1)")));

            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");

            ArrayList<String> helperUrls = new ArrayList<>();
            SelUtils.addToArrayList(connectionUrls, helperUrls, urls, i);
            ArrayList<String> helperNames = new ArrayList<>();
            SelUtils.addToArrayListText(connectionNames, helperNames, names, i);

            if(!SelUtils.ifElementExists(driver, "button[class*=pagination__button--next]")) {
                break;
            } else if(!nextBtn.isEnabled()) {
                break;
            } else {
                nextBtn.click();
            }

        }
    }






}
