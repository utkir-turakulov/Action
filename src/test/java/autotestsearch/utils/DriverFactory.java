package autotestsearch.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver create(String hubAddress) throws IOException {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        WebDriver driver = new RemoteWebDriver(new URL(hubAddress), capabilities);
        return driver;
    }
}
