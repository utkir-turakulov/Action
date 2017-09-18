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

/**
 *    Класс page object для верхней навигационной панели сайта
 */
public class HeaderPanel {

    private WebDriver driver;

    //кнопка Вход и регистрация
    @FindBy(id = "user-enter")
    private WebElement signOrLogInButton;

    //пункт меню Правовая база
    @FindBy(css = "a.btn_content_law")
    private WebElement contentLawButton;

    private static final String SIGN_OR_ENTER = "Вход и регистрация";

    public HeaderPanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean loggedOnCheck() throws TimeoutException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        wait.until(ExpectedConditions.visibilityOf(signOrLogInButton));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(signOrLogInButton, SIGN_OR_ENTER)));
        return true;
    }

    @Step("Getting to the authentication page")
    public void goToAuthentication() throws TimeoutException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        wait.until(ExpectedConditions.elementToBeClickable(signOrLogInButton));
        signOrLogInButton.click();
    }

    @Step("Getting to the content law page")
    public void goToContentLawPage() throws TimeoutException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        wait.until(ExpectedConditions.elementToBeClickable(contentLawButton));
        contentLawButton.click();
    }
}
