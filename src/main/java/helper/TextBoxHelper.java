package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxHelper {
	
	private static TextBoxHelper textBoxHelper;
	private static WebDriver wdDriver;

	private TextBoxHelper(WebDriver driver){
		wdDriver = driver;
	}
	
	public static TextBoxHelper getInstance(WebDriver driver){
		if(textBoxHelper == null || wdDriver.hashCode() != driver.hashCode())
			textBoxHelper = new TextBoxHelper(driver);
		return textBoxHelper;
	}
	
	public void setText(WebElement element,String value){
		element.sendKeys(value);
	}

	public String getText(WebElement element){
		return element.getText();
	}

	public void clear(WebElement element){
		element.clear();
	}
}
