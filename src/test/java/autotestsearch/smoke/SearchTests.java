package autotestsearch.smoke;

import autotestsearch.pages.AuthPage;
import autotestsearch.pages.ContentLawPage;
import autotestsearch.pages.HeaderPanel;
import autotestsearch.pages.SearchPanel;
import autotestsearch.utils.DriverFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;

public class SearchTests {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({"hostname", "hubAddress"})
    void setup(String hostname, String hubAddress) {
        try {
            driver = DriverFactory.create(hubAddress);
            driver.get(hostname);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Failed to connect with " + hubAddress + "\n" + e.getMessage());
        }
    }

    /**
     * Тест поиска документов указанного типа в указанном регионе по ключевому слову
     */
    @Title("Searching for documents with given type, region and key word")
    @Test
    @Parameters({"login", "password"})
    public void searchTest(String login, String password) {
        AuthPage authPage = new AuthPage(driver);
        ContentLawPage contentLawPage = new ContentLawPage(driver);
        HeaderPanel headerPanel = new HeaderPanel(driver);
        if(authPage.userAuth(login, password)) {
            headerPanel.goToContentLawPage();
            contentLawPage.searchForInfo("налог", "Постановление", "Москва");
            Assert.assertTrue(contentLawPage.checkSearchResults("налог", "Постановление", "Москва"));
        }
        else Assert.assertTrue(false, "Failed to log in");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
