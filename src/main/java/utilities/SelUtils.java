package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelUtils {


    public static void waitToClickAndClick(WebElement element, WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void waitAndSendKeys(WebElement element, String inputString, WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(inputString);

    }

    public static void waitGetFromListClick(List<WebElement> elements, int whichOne, WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements))
                .get(whichOne)
                .click();

    }

    public static void focusAndClick(WebElement element, WebDriver driver, WebDriverWait wait){
        Actions builder = new Actions(driver);
        Action seriesOfActions = builder
                .moveToElement(element)
                .click()
                .build();

        seriesOfActions.perform() ;

    }

    public static void addToArrayList(List<WebElement> list, ArrayList<String> helper, ArrayList<String> totalUrls, int i){
        for (WebElement element : list) {
            String elementHref = element.getAttribute("href");
            System.out.println("HELPER " + elementHref);
            helper.add(elementHref);
        }
        totalUrls.addAll(helper);
        System.out.println("--------------------------");
        System.out.println(i);
        System.out.println("--------------------------");
        System.out.println(helper);
    }

    public static void addToArrayListText(List<WebElement> list, ArrayList<String> helper, ArrayList<String> totalUrls, int i){
        for (WebElement element : list) {
            String elementText = element.getText();
            System.out.println("HELPER " + elementText);
            helper.add(elementText);
        }
        totalUrls.addAll(helper);
        System.out.println("--------------------------");
        System.out.println(i);
        System.out.println("--------------------------");
        System.out.println(helper);
    }

    public static boolean ifElementExists(WebDriver driver, String cssLocator){
        return driver.findElements( By.cssSelector(cssLocator) ).size() != 0;
    }

    public static String getCurrentDate(){
        LocalDate localDate = LocalDate.now();
        return  localDate.toString();
    }
}
