package autotestsearch.pages;

import autotestsearch.utils.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *    Класс page object для панели расширенного поиска
 */
public class SearchPanel {

    //строка поиска
    @FindBy(id = "search-text")
    private WebElement searchTextField;

    //кнопка По реквизитам
    @FindBy(id = "search-button-extended")
    private WebElement extendedSearchButton;

    //форма поиска по реквизитам
    @FindBy(id = "search-form-extended")
    private WebElement extendedSearchForm;

    //кнопка Найти
    @FindBy(id = "button-search-extended")
    private WebElement findButton;

    //локатор для поиска необходимого типа документа
    private static final String TYPE_LIST_LOCATOR = "//*[@id='typelist']/li[text()='%s']";

    //локатор для поиска необходимого региона
    private static final String REGION_LIST_LOCATOR = "//*[@id='regionlist']/li[text()='%s']";

    private WebDriver driver;

    public SearchPanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Opening the extended search panel")
    public void openExtendedSearchPanel() throws TimeoutException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        try {
            wait.until(ExpectedConditions.visibilityOf(extendedSearchForm));
        } catch (TimeoutException e) {
            wait.until(ExpectedConditions.visibilityOf(extendedSearchButton));
            extendedSearchButton.click();
        }
    }

    @Step("Filling the fields with given information")
    public void findInfo(String searchString, String documentType, String region) throws TimeoutException {
        openExtendedSearchPanel();
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        wait.until(ExpectedConditions.visibilityOf(searchTextField));
        searchTextField.sendKeys(searchString);
        searchTextField.sendKeys(Keys.TAB);

        By documentTypeLocator = By.xpath(String.format(TYPE_LIST_LOCATOR, documentType));
        WebElement documentTypeEntry = driver.findElement(documentTypeLocator);

        By regionLocator = By.xpath(String.format(REGION_LIST_LOCATOR, region));
        WebElement regionEntry = driver.findElement(regionLocator);

        wait.until(ExpectedConditions.elementToBeClickable(documentTypeEntry));
        documentTypeEntry.click();
        wait.until(ExpectedConditions.elementToBeClickable(regionEntry));
        regionEntry.click();
        wait.until(ExpectedConditions.elementToBeClickable(findButton));
        findButton.click();
    }
}
