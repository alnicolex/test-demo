package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneralUtils;

/**
 * Class with the elements and functionalities of the home page
 */
public class HomePage {

    public By register = By.xpath("//td/a[@href='register.php']");
    public By imgHome = By.xpath("//img[contains(@src,'images/featured_destination.gif')]");

    private WebDriver driver;
    private GeneralUtils utils;

    /**
     * Class constructor
     * @param driver
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        utils = new GeneralUtils(this.driver);
    }

    /**
     * Check page
     * @return
     */
    public Boolean loadPage(){
        return utils.existsElement(imgHome);
    }

    /**
     * Click registration option
     * @return
     */
    public RegisterPage clickRegister(){
        utils.maximize();
        utils.waitForSeconds();
        utils.click(register);
        return new RegisterPage(driver);
    }

}
