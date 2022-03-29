package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Selenium web driver overview class
 */
public class GeneralUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Class constructor
     * @param driver
     */
    public GeneralUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    /**
     *
     * @param locator
     * @param inputText
     */
    public void input(By locator, String inputText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(inputText);
        waitForSeconds();
    }

    /**
     * Click elemento web
     * @param locator
     */
    public void click(By locator) {
        try {
            WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
            waitClick.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            waitClick.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            waitClick.until(ExpectedConditions.elementToBeClickable(locator));
            this.driver.findElement(locator).click();
        } catch (StaleElementReferenceException var4) {
            WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
            waitClick.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            waitClick.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            waitClick.until(ExpectedConditions.elementToBeClickable(locator));
            this.driver.findElement(locator).click();
        }
        waitForSeconds();
    }

    /**
     * Click elemento web
     * @param element
     */
    public void click(WebElement element) {
        try {
            WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
            waitClick.until(ExpectedConditions.visibilityOf(element));
            waitClick.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException var4) {
            WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
            waitClick.until(ExpectedConditions.visibilityOf(element));
            waitClick.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
        waitForSeconds();
    }


    /**
     * Select list option
     * @param locator
     * @param optionSelect
     */
    public void selectOptionList(By locator, String optionSelect) {
        waitforloadElement(this.driver, locator);
        this.click(locator);
        String xpathExpression = "//option[text()='" + optionSelect + "']";
        WebElement lista = driver.findElement(locator);
        waitforloadElement(this.driver, By.xpath(xpathExpression));
        WebElement option = lista.findElement(By.xpath(xpathExpression));
        this.click(option);
        waitForSeconds();
    }

    /**
     * Wait for load element
     * @param driver
     * @param localizador
     */
    public void waitforloadElement(WebDriver driver, By localizador) {
        WebDriverWait espera = new WebDriverWait(driver, 4L);

        try {
            espera.until(ExpectedConditions.visibilityOfElementLocated(localizador));
            espera.until(ExpectedConditions.presenceOfElementLocated(localizador));
            espera.ignoring(NoSuchElementException.class);
        } catch (StaleElementReferenceException var4) {
            espera.until(ExpectedConditions.visibilityOfElementLocated(localizador));
            espera.until(ExpectedConditions.presenceOfElementLocated(localizador));
            espera.ignoring(NoSuchElementException.class);
        }
        waitForSeconds();
    }

    /**
     * Wait for 2 seconds
     */
    public void waitForSeconds() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 2L, 1L);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException var2) {
        }

    }

    /**
     * Maximize display
     */
    public void maximize() {
        this.driver.manage().window().maximize();
    }

    /**
     * Get text web element
     * @param locator
     * @return
     */
    public String getText(By locator) {
        WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
        waitClick.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        waitClick.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return this.driver.findElement(locator).getText();
    }

    /**
     * Find elements web
     * @param locator
     * @return
     */
    public List<WebElement> findElements(By locator) {
        return this.driver.findElements(locator);
    }

    /**
     * Check element
     * @param locator
     * @return
     */
    public Boolean existsElement(By locator){
        WebDriverWait waitClick = new WebDriverWait(this.driver, 10);
        waitClick.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        waitClick.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return this.driver.findElement(locator).isDisplayed();
    }

    /**
     * Capture screenshot
     * @param imgName
     * @throws IOException
     */
    public void captureScreen(String imgName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot/" + imgName + ".jpg"));
    }
}
