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
 *    Класс page object для страницы авторизации пользователей
 */
public class AuthPage {

    //вкладка Войдите на сайт под своей почтой и паролем
    @FindBy(id = "wf-enter")
    private WebElement logInTab;

    //поле для ввода логина/емейла
    @FindBy(id = "email")
    private WebElement loginField;

    //поле для ввода пароля
    @FindBy(id = "password")
    private WebElement passwordField;

    //кнопка Войти
    @FindBy(id = "customer-enter")
    private WebElement logInButton;

    private HeaderPanel headerPanel;

    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headerPanel = new HeaderPanel(driver);
    }

    @Step("Sending user credentials")
    public boolean userAuth(String userLogin, String userPass) throws TimeoutException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, Constants.TIMEOUT).withMessage(Constants.NOT_FOUND_MESSAGE);
        headerPanel.goToAuthentication();
        wait.until(ExpectedConditions.elementToBeClickable(logInTab));
        logInTab.click();
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(userLogin);
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(userPass);
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
        return headerPanel.loggedOnCheck();
    }
}
