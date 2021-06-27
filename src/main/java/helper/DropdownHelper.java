package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownHelper {

    private static DropdownHelper drodownHelper;
    private static WebDriver wdDrvier;

    private DropdownHelper(WebDriver driver) {
        wdDrvier = driver;
    }

    public static DropdownHelper getInstance(WebDriver driver) {
        if (drodownHelper == null || wdDrvier.hashCode() != driver.hashCode())
            drodownHelper = new DropdownHelper(driver);
        return drodownHelper;
    }

    public void selectByVisibleText(WebElement element, String visibleValue) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleValue);
    }

}
