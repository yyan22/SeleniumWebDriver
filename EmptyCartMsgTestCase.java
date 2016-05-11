package automationFramework;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EmptyCartMsgTestCase {
    private WebDriver driver;
    private OnlineStorePage page;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
	page = new OnlineStorePage(driver); 
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
    }

    @Test
    public void testEmptyCartMsg() throws Exception {
        page.open("http://store.demoqa.com");
	driver.findElement(By.linkText("All Product")).click();
	  
	// Add 3 products to shopping cart   
	WebElement firstProduct_add = driver.findElement(By.cssSelector("form[name=\"product_32\"] > div.wpsc_buy_button_container.group > div.input-button-buy > span > input[name=\"Buy\"]"));	  
	page.addProduct(firstProduct_add);
      
	driver.findElement(By.linkText("Continue Shopping")).click();
	  
	WebElement secondProduct_add = driver.findElement(By.cssSelector("form[name=\"product_40\"] > div.wpsc_buy_button_container.group > div.input-button-buy > span > input[name=\"Buy\"]"));
	page.addProduct(secondProduct_add);
 
	driver.findElement(By.linkText("Continue Shopping")).click();
	  
	WebElement thirdProduct_add = driver.findElement(By.cssSelector("form[name=\"product_64\"] > div.wpsc_buy_button_container.group > div.input-button-buy > span > input[name=\"Buy\"]"));
	page.addProduct(thirdProduct_add);
   
	driver.findElement(By.linkText("Go to Checkout")).click();
	  
	// Remove all 3 products from shopping cart
	WebElement firstProduct_remove = driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]"));
	page.removeProduct(firstProduct_remove);
	WebElement secondProduct_remove = driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]"));
	page.removeProduct(secondProduct_remove);
	WebElement thirdProduct_remove = driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]"));
	page.removeProduct(thirdProduct_remove);
	  
	// Verify removing all products from shopping cart produces empty cart message
	String actualMessage = driver.findElement(By.cssSelector("div#content > article#post-29 > div.entry-content")).getText();
	String expectedMessage = "Oops, there is nothing in your cart.";
	Assert.assertEquals(actualMessage, expectedMessage);
	System.out.println("Removing all items from your cart produces an empty cart message.");
    }
     
    @After
    public void tearDown() throws Exception {
        page.close();
    }
     
}
