package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import pages.CommonBankHomePage;
import pages.RepaymentCalculatorPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class InterestCalculatorStepDef {
    WebDriver driver;
    RepaymentCalculatorPage repaymentCalculatorPage;
    CommonBankHomePage commonBankHomePage;
    Logger logger = LogManager.getLogger(InterestCalculatorStepDef.class);

    @Before
    public void setup() throws IOException {
        if (getPropertyValue("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            logger.info("Chrome browser started");
        } else if (getPropertyValue("browser").equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
            logger.info("Firefox browser started");
        } else {
            logger.error("Invalid browser");
            throw new RuntimeException("Invalid browser type : " + getPropertyValue("browser"));
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I have the application url")
    public void iHaveTheApplicationUrl() throws IOException, InterruptedException {
        String url = getPropertyValue("url");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        logger.info("Application URL : " + url);
        driver.manage().window().maximize();
    }

    @When("I click on Repayments calculator")
    public void iClickOn() {
        commonBankHomePage = new CommonBankHomePage(driver);
        repaymentCalculatorPage = commonBankHomePage.clickOnRepaymentCalculator();
    }

    @Then("I verify the {string} page is open")
    public void iVerifyThePageIsOpen(String expectedPageTitle) {
        String actualTitle = driver.getTitle();
        logger.info("Expected Page Title : " + expectedPageTitle);
        logger.info("Actual Page Title : " + actualTitle);
        Assert.assertEquals(actualTitle, expectedPageTitle);
    }

    @When("I input the borrowed amount as {string}")
    public void iInputTheBorrowedAmountAs(String amount) {
        logger.info("Loan amount : " + amount);
        repaymentCalculatorPage.inputBorrowedAmount(amount);
    }

    @And("I input the borrowed period as {string}")
    public void iInputTheBorrowedPeriodAs(String period) {
        logger.info("Loan period : " + period);
        repaymentCalculatorPage.inputBorrowedPeriod(period);
    }

    @And("I input the repayment type as {string}")
    public void iInputTheRepaymentTypeAs(String type) {
        logger.info("Repayment Type : " + type);
        repaymentCalculatorPage.inputRepaymentType(type);
    }

    @And("I input the interest rate as {string}")
    public void iInputTheInterestRateAs(String interestRate) {
        logger.info("Interest rate : " + interestRate);
        repaymentCalculatorPage.inputInterestRate(interestRate);
    }

    @And("I click on calculate button")
    public void iClickOnCalculateButton() {
        repaymentCalculatorPage.clickOnCalculate();
    }


    @Then("I verify the total loan repayment amount is {string}")
    public void iVerifyTheTotalLoanRepaymentAmountIs(String expectedLoanRepaymentAmount) {
        String actualLoanRepaymentAmount = repaymentCalculatorPage.getTotalLoanRepaymentAmount();
        logger.info("Expected Loan Repayment Amount : " + expectedLoanRepaymentAmount);
        logger.info("Actual Loan Repayment Amount : " + actualLoanRepaymentAmount);
        Assert.assertEquals(actualLoanRepaymentAmount, expectedLoanRepaymentAmount);
    }

    @Then("I verify the total interest charged amount is {string}")
    public void iVerifyTheTotalInterestChargedAmountIs(String expectedInterestAmount) {
        String actualInterestAmount = repaymentCalculatorPage.getTotalInterestAmount();
        logger.info("Expected Interest Amount : " + expectedInterestAmount);
        logger.info("Actual Interest Amount : " + actualInterestAmount);
        Assert.assertEquals(actualInterestAmount, expectedInterestAmount);
    }

    private String getPropertyValue(String key) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
}
