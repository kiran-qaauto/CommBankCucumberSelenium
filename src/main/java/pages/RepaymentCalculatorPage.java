package pages;

import helper.ButtonHelper;
import helper.DropdownHelper;
import helper.TextBoxHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RepaymentCalculatorPage {
    WebDriver driver;
    DropdownHelper dropdownHelper;
    TextBoxHelper textBoxHelper;
    ButtonHelper buttonHelper;


    public RepaymentCalculatorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        dropdownHelper = DropdownHelper.getInstance(driver);
        textBoxHelper = TextBoxHelper.getInstance(driver);
        buttonHelper = ButtonHelper.getInstance(driver);
    }

    @FindBy(css = "input#amount")
    WebElement loanAmount;

    @FindBy(css = "input#term")
    WebElement term;

    @FindBy(xpath = "//select[@id='interestOnly']")
    WebElement repaymentType;

    @FindBy(xpath = "//select[@id='productId']")
    WebElement interestRate;

    @FindBy(id = "submit")
    WebElement calculateButton;

    @FindBy(xpath = "//span[@data-tid='total-repayment']")
    WebElement totalRepaymentAmount;

    @FindBy(xpath = "//span[@data-tid='total-interest']")
    WebElement totalInterestAmount;


    public void inputBorrowedAmount(String amount){
        textBoxHelper.clear(loanAmount);
        textBoxHelper.setText(loanAmount, amount);
    }

    public void inputBorrowedPeriod(String period){
        textBoxHelper.clear(term);
        textBoxHelper.setText(term, period);
    }


    public void inputRepaymentType(String type) {
        dropdownHelper.selectByVisibleText(repaymentType, type);
    }

    public void inputInterestRate(String interestRateValue) {
        dropdownHelper.selectByVisibleText(interestRate, interestRateValue);
    }

    public void clickOnCalculate() {
        buttonHelper.click(calculateButton);
    }

    public String getTotalLoanRepaymentAmount() {
        return textBoxHelper.getText(totalRepaymentAmount);
    }

    public String getTotalInterestAmount() {
        return textBoxHelper.getText(totalInterestAmount);
    }

    protected WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }
}
