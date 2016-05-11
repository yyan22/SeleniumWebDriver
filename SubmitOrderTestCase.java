package automationFramework;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SubmitOrderTestCase {
  private WebDriver driver;
  private OnlineStorePage page;
  private String baseUrl;
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    page = new OnlineStorePage(driver);
    baseUrl = "http://store.demoqa.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void testSubmitOrder() throws Exception {
    page.open(baseUrl);
	
    WebElement element = driver.findElement(By.linkText("Product Category"));
    
    Actions action = new Actions(driver);
    action.moveToElement(element).build().perform();
    driver.findElement(By.linkText("iPhones")).click();
    
    // Add product to shopping cart
    driver.findElement(By.cssSelector("form[name=\"product_96\"] > div.wpsc_buy_button_container.group > div.input-button-buy > span > input[name=\"Buy\"]")).click();

    // Save product price on description page for later use
    WebElement listedPriceElm = driver.findElement(By.cssSelector("form[name=\"product_96\"] > div.wpsc_product_price > p.pricedisplay_oldprice > span.currentprice"));
    String listedPrice = listedPriceElm.getText();
    
    driver.findElement(By.linkText("Go to Checkout")).click();
    driver.findElement(By.cssSelector("a.step2 > span")).click();

    new Select(driver.findElement(By.id("current_country"))).selectByVisibleText("USA");
    
    WebElement stateForShipping = driver.findElement(By.name("collected_data[15]"));
    page.typeUserInformation(stateForShipping, "TX");
    WebElement zipForShipping = driver.findElement(By.name("wpsc_submit_zipcode"));
    zipForShipping.click();
    
    // Enter user information at checkout
    WebElement email = driver.findElement(By.id("wpsc_checkout_form_9"));
    page.typeUserInformation(email, "eTest@gmail.com");
    WebElement firstName = driver.findElement(By.id("wpsc_checkout_form_2"));
    page.typeUserInformation(firstName, "fTest");
    WebElement lastName = driver.findElement(By.id("wpsc_checkout_form_3"));
    page.typeUserInformation(lastName, "lTest");
    WebElement address = driver.findElement(By.id("wpsc_checkout_form_4"));
    page.typeUserInformation(address, "111 aTest St");
    WebElement city = driver.findElement(By.id("wpsc_checkout_form_5"));
    page.typeUserInformation(city, "Austin");
    WebElement state = driver.findElement(By.id("wpsc_checkout_form_6"));
    page.typeUserInformation(state, "TX");
    
    new Select(driver.findElement(By.id("wpsc_checkout_form_7"))).selectByVisibleText("USA");
    
    WebElement zip = driver.findElement(By.id("wpsc_checkout_form_8"));
    page.typeUserInformation(zip, "78759");
    WebElement phone = driver.findElement(By.id("wpsc_checkout_form_18"));
    page.typeUserInformation(phone, "512-111-1111");
    
    driver.findElement(By.id("shippingSameBilling")).click();
    
    // Verify the product price at checkout is the same as the one on product description page
    WebElement actualPriceElm = driver.findElement(By.cssSelector("div.review > table.wpsc_checkout_table > tbody > tr.total_item > td.wpsc_totals > span.checkout-shipping > span.pricedisplay"));
    Assert.assertEquals(actualPriceElm.getText(), listedPrice);
    
    WebElement submitElem = driver.findElement(By.cssSelector("span > input[name=\"submit\"]"));
    page.submit(submitElem);
    
    System.out.println("Apple iPhone4s 16GB SIM-Free - Black order has been successfully placed.");
  }

  @After
  public void tearDown() throws Exception {
    page.close();
  }

}
