package properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverInitialization {
    public DriverInitialization() {

    }

    public WebDriver getDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        firefoxOptions.setHeadless(true);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }




}
