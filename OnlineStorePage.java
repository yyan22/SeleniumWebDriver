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
		elem.click();
		elem.clear();
		elem.sendKeys(userInput);
		elem.sendKeys(Keys.TAB);
		//executor.executeScript("$(arguments[0]).change();", elem);
	}
	
	public void submit(WebElement elem) {
	    elem.submit();
	}
	
	public void login() {
	    WebElement username = driver.findElement(By.id("log"));
	    this.typeUserInformation(username, "yyan");
	    WebElement password = driver.findElement(By.id("pwd"));
	    this.typeUserInformation(password, "Happy4ever");
	    WebElement loginElem = driver.findElement(By.id("login"));
		executor.executeScript("arguments[0].click();", loginElem);
	}
	
	public void logout() {
		WebElement logoutElem = driver.findElement(By.linkText("(Logout)"));
		executor.executeScript("arguments[0].click();", logoutElem);
	}
	
	public void addProduct(WebElement elem) {
		executor.executeScript("arguments[0].click();", elem);
	}
	
    public void removeProduct(WebElement elem) {
    	elem.click();
    }
}
