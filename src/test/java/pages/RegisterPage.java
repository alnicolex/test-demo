package pages;

import dto.FormDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneralUtils;

import java.io.IOException;

/**
 * Class with the elements and functionalities of the registration page
 */
public class RegisterPage {

    public By name = By.xpath("//input[@name=\"firstName\"]");
    public By lastname = By.xpath("//input[@name=\"lastName\"]");
    public By phone= By.xpath("//input[@name=\"phone\"]");
    public By email = By.id("userName");
    public By address = By.xpath("//input[@name=\"address1\"]");
    public By city = By.xpath("//input[@name=\"city\"]");
    public By state = By.xpath("//input[@name=\"state\"]");
    public By codePostal = By.xpath("//input[@name=\"postalCode\"]");
    public By country = By.xpath("//select[@name=\"country\"]");
    public By user = By.id("email");
    public By password = By.xpath("//input[@name=\"password\"]");
    public By confirmPassword = By.xpath("//input[@name=\"confirmPassword\"]");
    public By btnsubmit =  By.xpath("//input[@name=\"submit\"]");;
    public By msjSuccessful = By.xpath("//b[contains(text(), 'Note: Your user name is')]");

    private WebDriver driver;
    private GeneralUtils utils;

    /**
     * Class constructor
     * @param driver
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        utils = new GeneralUtils(this.driver);

    }

    /**
     * Check page
     * @return
     */
    public Boolean loadPage(){
        return utils.existsElement(name);
    }

    /**
     * Enter data
     * @param dto Form
     * @throws IOException
     */
    public void register(FormDto dto) throws IOException {
        utils.maximize();
        utils.input(name, dto.getName());
        utils.input(lastname, dto.getLastname());
        utils.input(phone, dto.getPhone());
        utils.input(email, dto.getEmail());
        utils.input(address, dto.getAddress());
        utils.input(city, dto.getCity());
        utils.input(state, dto.getState());
        utils.input(codePostal, dto.getCodePostal());
        utils.selectOptionList(country, dto.getCountry());
        utils.input(user, dto.getUser());
        utils.input(password, dto.getPassword());
        utils.input(confirmPassword, dto.getConfirmPassword());
        utils.captureScreen("register");
    }

    /**
     * Submit register
     * @throws IOException
     */
    public void submit() throws IOException {
        utils.click(btnsubmit);
        utils.captureScreen("confirmation");
    }

    /**
     * Check register
     * @return
     * @throws IOException
     */
    public String successfulMessage() throws IOException {
        utils.waitForSeconds();
        utils.captureScreen("result");
        return utils.getText(msjSuccessful);
    }
}
