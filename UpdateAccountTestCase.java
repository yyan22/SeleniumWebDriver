package automationFramework;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class UpdateAccountTestCase {
  private WebDriver driver;
  private OnlineStorePage page;
  private String baseUrl;
  WebDriverWait wait;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    page = new OnlineStorePage(driver);
    baseUrl = "http://store.demoqa.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, 30);
  }

  @Test
  public void testUpdateAccount() throws Exception {	  
    page.open(baseUrl);
	
    // Go to "My Account" page
    driver.findElement(By.cssSelector("div#account > a.account_icon")).click();
    
    // Login
    page.login();
    
    // Go to "Your Details" page
    driver.findElement(By.linkText("Your Details")).click();
    
    // Modify account details
    WebElement firstNameElm = driver.findElement(By.id("wpsc_checkout_form_2"));
    page.typeUserInformation(firstNameElm, "fTest");
    String firstName_saved = "fTest";
    
    WebElement lastNameElm = driver.findElement(By.id("wpsc_checkout_form_3"));
    page.typeUserInformation(lastNameElm, "lTest");
    String lastName_saved = "lTest";
    		
    WebElement addressElm = driver.findElement(By.id("wpsc_checkout_form_4"));
    page.typeUserInformation(addressElm, "111 aTest St");
    String address_saved = "111 aTest St";
    
    WebElement cityElm = driver.findElement(By.id("wpsc_checkout_form_5"));
    page.typeUserInformation(cityElm, "Austin");
    String city_saved = "Austin";
    
    new Select(driver.findElement(By.id("wpsc_checkout_form_7"))).selectByVisibleText("USA");
    String country_saved = "USA";

    WebElement zipElm = driver.findElement(By.id("wpsc_checkout_form_8"));
    page.typeUserInformation(zipElm, "78759");
    String zip_saved = "78759";
    
    WebElement phoneElm = driver.findElement(By.id("wpsc_checkout_form_18"));
    page.typeUserInformation(phoneElm, "512-111-1111");
    String phone_saved = "512-111-1111";
    
    WebElement shippingSameBillingElm = driver.findElement(By.id("shippingSameBilling"));
    boolean shippingSameBilling = shippingSameBillingElm.isSelected();
    if (!shippingSameBilling) {   	
    	//shippingSameBillingElm.click();
    }
    //Thread.sleep(1000);
    // Toggle "Same as billing address" check box to verify billing information is the same as shipping information
    //shippingSameBillingElm.click();
    //Thread.sleep(5000);
    // Verify billing address is the same as shipping address after the "Same as billing address" check box is checked
/*
    String firstName_billing = driver.findElement(By.id("wpsc_checkout_form_11")).getAttribute("value");
    assertEquals(firstName_saved, firstName_billing);  
    
    String lastName_billing = driver.findElement(By.id("wpsc_checkout_form_12")).getAttribute("value");
    assertEquals(lastName_saved, lastName_billing);
    
    String address_billing = driver.findElement(By.id("wpsc_checkout_form_13")).getAttribute("value");
    assertEquals(address_saved, address_billing);
    
    String city_billing = driver.findElement(By.id("wpsc_checkout_form_14")).getAttribute("value");
    assertEquals(city_saved, city_billing);

    String zip_billing = driver.findElement(By.id("wpsc_checkout_form_17")).getAttribute("value");
    assertEquals(zip_saved, zip_billing);
    Thread.sleep(5000);
    shippingSameBillingElm.click();
 */   
    // Save account details
    WebElement submitElem = driver.findElement(By.name("submit"));
    wait.until(elementToBeClickable(submitElem));
    page.submit(submitElem);
    
    //Thread.sleep(1000);
    
    // Logout
    page.logout();
    //driver.findElement(By.linkText("(Logout)")).click();
    
    // Log back in
    page.login();
    
    driver.findElement(By.linkText("Your Details")).click();
    
    // Compare values to verify that account details have been successfully saved
    String firstName = driver.findElement(By.id("wpsc_checkout_form_2")).getAttribute("value");
    assertEquals(firstName_saved, firstName);  
    
    String lastName = driver.findElement(By.id("wpsc_checkout_form_3")).getAttribute("value");
    assertEquals(lastName_saved, lastName);
    
    String address = driver.findElement(By.id("wpsc_checkout_form_4")).getAttribute("value");
    assertEquals(address_saved, address);
    
    String city = driver.findElement(By.id("wpsc_checkout_form_5")).getAttribute("value");
    assertEquals(city_saved, city);
    
    String country = new Select(driver.findElement(By.id("wpsc_checkout_form_7"))).getFirstSelectedOption().getText();
    assertEquals(country_saved, country);
    
    String zip = driver.findElement(By.id("wpsc_checkout_form_8")).getAttribute("value");
    assertEquals(zip_saved, zip);
    
    String phone = driver.findElement(By.id("wpsc_checkout_form_18")).getAttribute("value");
    assertEquals(phone_saved, phone);
    
    shippingSameBilling = driver.findElement(By.id("shippingSameBilling")).isSelected();
    assertTrue(shippingSameBilling);
    
    System.out.println("Account details are saved and retrieved.");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

}
