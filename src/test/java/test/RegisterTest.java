package test;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.FormDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.RegisterPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegisterTest {

    private WebDriver driver;
    private HomePage pageHome;
    private RegisterPage pageRegister;
    private FormDto formDto;


    @Given("The user accesses the home page")
    public void theUserIsInHomePage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        pageHome = new HomePage(driver);
        formDto = new FormDto();
        driver.get("http://demo.guru99.com/test/newtours/");
    }

    @When("The home page appears")
    public void homePage(){
        Assert.assertTrue(pageHome.loadPage(), "Error home page");
    }

    @When("The user clicks on the registration option")
    public void theUserClickOnRegisterOption(){
        pageRegister = pageHome.clickRegister();
    }

    @When("The register page appears")
    public void registerPage(){
        Assert.assertTrue(pageRegister.loadPage(), "Error page register");
    }

    @When("The user enters information into the form")
    public void enterInformation(DataTable dataTable) throws IOException {

        List<Map<String, String>> form = dataTable.asMaps(String.class, String.class);

        formDto.setName(form.get(0).get("name"));
        formDto.setLastname(form.get(0).get("lastname"));
        formDto.setPhone(form.get(0).get("phone"));
        formDto.setEmail(form.get(0).get("email"));
        formDto.setAddress(form.get(0).get("address"));
        formDto.setCity(form.get(0).get("city"));
        formDto.setState(form.get(0).get("state"));
        formDto.setCodePostal(form.get(0).get("codePostal"));
        formDto.setCountry(form.get(0).get("country"));
        formDto.setUser(form.get(0).get("user"));
        formDto.setPassword(form.get(0).get("password"));
        formDto.setConfirmPassword(form.get(0).get("confirmPassword"));
        pageRegister.register(formDto);
    }

    @When("The user clicks on submit")
    public void submit() throws IOException {
        pageRegister.submit();
    }

    @Then("The record is generated in the system")
    public void recordIsGenerated() throws IOException {
        String msg = "Note: Your user name is ";
        Assert.assertEquals(pageRegister.successfulMessage(), msg.concat(formDto.getUser()).concat("."), "Error registro no exitoso");
        driver.close();
    }

}
