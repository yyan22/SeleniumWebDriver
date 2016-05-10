package automationFramework;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UpdateAccountTestCase {
  private WebDriver driver;
  private OnlineStorePage page;
  private String baseUrl;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    page = new OnlineStorePage(driver);
    baseUrl = "http://store.demoqa.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUpdateAccount() throws Exception {	  
	page.open(baseUrl);
	
	// Go to "My Account" page
    driver.findElement(By.cssSelector("div#account > a.account_icon")).click();
    
    // Login
    WebElement username = driver.findElement(By.id("log"));
    page.typeUserInformation(username, "yyan");
    WebElement password = driver.findElement(By.id("pwd"));
    page.typeUserInformation(password, "Happy4ever");
    driver.findElement(By.id("login")).click();
    
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
    
    driver.findElement(By.id("shippingSameBilling")).click();
    
    // Save account details
    driver.findElement(By.name("submit")).click();
    
    // Logout
    driver.findElement(By.linkText("(Logout)")).click();
    
    // Log back in
    username = driver.findElement(By.id("log"));
    page.typeUserInformation(username, "yyan");
    password = driver.findElement(By.id("pwd"));
    page.typeUserInformation(password, "Happy4ever");
    driver.findElement(By.id("login")).click();
    
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
    
    boolean shippingSameBilling = driver.findElement(By.id("shippingSameBilling")).isSelected();
    assertTrue(shippingSameBilling);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

}
