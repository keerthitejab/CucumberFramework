package stepdefinitions;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Login {
    private Logger log ;
    WebDriver driver;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private CommonUtils commonUtils;

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() {
        log=LogManager.getLogger(this.getClass().getName());
        driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        loginPage = homePage.selectLoginOption();
        log.info("User navigated to Login Page");
    }


    @When("^User enters valid email address (.+) into email field$")
    public void User_enters_valid_email_address_into_email_field(String emailText) {

        loginPage.enterEmailAddress(emailText);

        log.info("User Enter the user name");

    }

    @And("^User enters valid password (.+) into password field$")
    public void user_enters_valid_password_into_password_field(String passwordText) {

        loginPage.enterPassword(passwordText);

        log.info("User Enter the Password");

    }

    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {

        accountPage = loginPage.clickOnLoginButton();
        log.info("User click the Login Button");

    }

    @Then("User should get successfully logged in")
    public void user_should_get_successfully_logged_in() {

        Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationOption());
        log.info("User successfully Logged In");
        log.error("Not successfully opened Account page");
    }

    @When("User enters invalid email address into email field")
    public void user_enters_invalid_email_address_into_email_field() {

        commonUtils = new CommonUtils();
        loginPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
        log.info("User Enter Invalid Email id");
    }

    @When("User enters invalid password {string} into password field")
    public void user_enters_invalid_password_into_password_field(String invalidPasswordText) {

        loginPage.enterPassword(invalidPasswordText);
        log.info("User Enter Invalid Password");
    }

    @Then("User should get a proper warning message about credentials mismatch")
    public void user_should_get_a_proper_warning_message_about_credentials_mismatch() {

        Assert.assertTrue(loginPage.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
        log.info("User able to warning message");
    }

    @When("User dont enter email address into email field")
    public void user_dont_enter_email_address_into_email_field() {

        loginPage.enterEmailAddress("");
        log.info("User not enter the email id");
    }

    @When("User dont enter password into password field")
    public void user_dont_enter_password_into_password_field() {

        loginPage.enterPassword("");
        log.info("User not entered the password");

    }


}
