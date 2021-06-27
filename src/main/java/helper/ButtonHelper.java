package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonHelper {
	
	private static ButtonHelper buttonHelper;
	private static WebDriver wdDriver;
	
	private ButtonHelper(WebDriver driver){
		wdDriver = driver;
	}
	
	public static ButtonHelper getInstance(WebDriver driver){
		if(buttonHelper == null || wdDriver.hashCode() != driver.hashCode())
			buttonHelper = new ButtonHelper(driver);
		return buttonHelper;
	}

	public void click(WebElement element){
		element.click();
	}
}
