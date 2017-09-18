package autotestsearch.pages;

import autotestsearch.utils.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

/**
 *    Класс page object для страницы "Правовая база"
 */
public class ContentLawPage {

    //список ссылок на найденные документы
    @FindBy(id = "searchResultsSection")
    private WebElement searchResultsSection;

    //название поисковой выдачи
    @FindBy(id = "ex-search-string")
    private WebElement searchTitle;

    //описание параметров поиска
    @FindBy(css = "div.search-extended-string")
    private WebElement searchResultDescriptionString;

    //результаты по разделам (боковая панель)
    @FindBy(css = "div.menu__title")
    private WebElement resultsSideBar;

    private static final String TITLE_PART = "Правовая база";

    private WebDriver driver;

    private HeaderPanel headerPanel;

    private SearchPanel searchPanel;

    public ContentLawPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headerPanel = new HeaderPanel(driver);
        searchPanel = new SearchPanel(driver);
    }

    @Step("Searching by given key words")
    public void searchForInfo(String searchString, String documentType, String region) {
        if (onPageCheck()) {
            searchPanel.findInfo(searchString, documentType, region);
        }
    }

    @Step("Checking search results")
    public boolean checkSearchResults() throws TimeoutException {
        try {
            final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
            wait.until(ExpectedConditions.visibilityOf(searchResultsSection));
            wait.until(ExpectedConditions.visibilityOf(searchTitle));
            wait.until(ExpectedConditions.visibilityOf(searchResultDescriptionString));
            wait.until(ExpectedConditions.visibilityOf(resultsSideBar));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean onPageCheck() {
        try {
            final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
            wait.until(ExpectedConditions.titleContains(TITLE_PART));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
