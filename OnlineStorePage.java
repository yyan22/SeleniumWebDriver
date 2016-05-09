package automationFramework;

import org.openqa.selenium.*;

public class OnlineStorePage {
	
	protected WebDriver driver;
	protected JavascriptExecutor executor;
	
	public OnlineStorePage(WebDriver driver) {
		this.driver = driver;
		this.executor = (JavascriptExecutor) driver;
	}
	
	public void open(String url) {
		driver.get(url);
	}
	
	public void close() {
		driver.quit();
	}
	
	public void typeUserInformation(WebElement elem, String userInput) {
		elem.clear();
		elem.sendKeys(userInput);
	}
	
	public void addProduct(WebElement elem) {
		executor.executeScript("arguments[0].click();", elem);
	}
	
    public void removeProduct(WebElement elem) {
    	elem.click();
    }
}
