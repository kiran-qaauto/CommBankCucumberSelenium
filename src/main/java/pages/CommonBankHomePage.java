package pages;

import helper.ButtonHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CommonBankHomePage {
    WebDriver driver;
    ButtonHelper buttonHelper;

    public CommonBankHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        buttonHelper = ButtonHelper.getInstance(driver);
    }

    @FindBy(linkText = "Repayments calculator")
    WebElement repaymentCalculator;


    public RepaymentCalculatorPage clickOnRepaymentCalculator(){
        RepaymentCalculatorPage repaymentCalculatorPage = new RepaymentCalculatorPage(driver);
        buttonHelper.click(repaymentCalculator);
        Wait<WebDriver> wait = getWait();
        wait.until(ExpectedConditions.elementToBeClickable(repaymentCalculatorPage.loanAmount));
        return repaymentCalculatorPage;
    }

    protected Wait<WebDriver> getWait(){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

}
