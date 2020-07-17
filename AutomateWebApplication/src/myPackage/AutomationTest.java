package myPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationTest {
	public String url = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public String email="test1_automationtest@gmail.com";
	
	@BeforeTest
	public void setUrl() {
		// Enter the webdriver path here.
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\mruna\\Downloads\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);
	}
	
	
	// Test for valid registration with proper inputs
	// In order to test this,enter a new email id for every account execution.
	@Test
    public void validRegistrationTest() throws Exception{            
              
              // enter new email address here
			  String emailCreate = "test15_automationtest@gmail.com";
			  
			  WebElement signin = driver.findElement(By.className("login"));
			  signin.click();
			
			  WebElement createEmail = driver.findElement(By.id("email_create"));
			  createEmail.sendKeys(emailCreate);
			
			  WebElement submit = driver.findElement(By.id("SubmitCreate"));
			  submit.click();
			 
			  WebDriverWait wait = new WebDriverWait(driver, 60);
		  	  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
		  
			  WebElement customerFirstname = driver.findElement(By.cssSelector("input#customer_firstname"));
			  customerFirstname.sendKeys("TestFirstName");
			  
			  WebElement customerLastname = driver.findElement(By.cssSelector("input#customer_lastname"));
			  customerLastname.sendKeys("TestLastName");
			  
			  WebElement password = driver.findElement(By.id("passwd"));
			  password.sendKeys("12345");
			  
			  WebElement company = driver.findElement(By.id("company"));
			  company.sendKeys("TestCompany");
			  
			  WebElement address1 = driver.findElement(By.id("address1"));
			  address1.sendKeys("Address1");
			  
			  WebElement address2 = driver.findElement(By.id("address2"));
			  address2.sendKeys("Address2");
			  
			  WebElement city = driver.findElement(By.id("city")); city.sendKeys("Los Angeles");
			  
			  Select dropdown = new Select(driver.findElement(By.id("id_state")));
			  dropdown.selectByVisibleText("California");
			  
			  WebElement postcode = driver.findElement(By.id("postcode"));
			  postcode.sendKeys("90017");
			  
			  WebElement country = driver.findElement(By.id("id_country"));
			  country.sendKeys("United States");
			  
			  WebElement phone = driver.findElement(By.id("phone_mobile"));
			  phone.sendKeys("9876543210");
			  
			  WebElement alias = driver.findElement(By.id("alias"));
			  alias.sendKeys("Myadrress");
			  
			  WebElement register = driver.findElement(By.id("submitAccount"));
			  register.click();
			  
			  String expectedTitle = "My account - My Store"; 
			  String actualTitle = driver.getTitle(); 
			  Assert.assertEquals(actualTitle, expectedTitle);
			  System.out.println("Your account with email_id : "+ emailCreate + " is registered.");
			  System.out.println("You are successfully logged in ");
			  driver.findElement(By.className("logout")).click();
			  Thread.sleep(4000);
			 }
	
	
	//Test case to check if invalid login attempts are handled when invalid credentials are entered
	 @Test(priority = 1)
	    public void failedLoginInvalidCredentials() throws Exception
	    {
	    	WebElement signin = driver.findElement(By.className("login"));
			signin.click();
	    	driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("passwd")).sendKeys("1234589");
			driver.findElement(By.id("SubmitLogin")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
		  	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
		  
		  	// check if the error message is generated 
			String actualErrorMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p")).getText();
			String expectedErrorMsg="There is 1 error";
			Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
			
			// also check if the title of the page does not change.
			String incorrectTitle = "My account - My Store"; 
			String actualTitle =  driver.getTitle(); 
			Assert.assertNotEquals(actualTitle, incorrectTitle);
			System.out.println("failed Login attempt. Enter proper credentials");	
			
			Thread.sleep(5000);
	    }	
	 
	// Test for valid login
	@Test(priority = 1)
	public void validLogin() throws InterruptedException {
		driver.findElement(By.className("login")).click();
	    driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.id("SubmitLogin")).click();
		String expectedTitle = "My account - My Store"; 
	    String actualTitle = driver.getTitle(); 
	    Assert.assertEquals(actualTitle, expectedTitle);
	    System.out.println("You are successfully logged in ");
	    Thread.sleep(5000);
	}
	
    // Test to enter an item in search box
	@Test(priority = 1)
	public void searchItem() throws InterruptedException {
		driver.findElement(By.id("search_query_top")).sendKeys("shirts");
		driver.findElement(By.name("submit_search")).click();

		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,100)");
		
		Thread.sleep(4000);
	}
	
	// Test to select a product from the shopping list and add it to cart
	@Test(priority = 2)
	public void addtoCart() {
		driver.findElement(By.id("search_query_top")).sendKeys("dress");
		driver.findElement(By.name("submit_search")).click();
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,200)");
		WebDriverWait wait = new WebDriverWait(driver, 60);
	  	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-container")));
	  	driver.findElement(By.cssSelector("a[href*='id_product=4']")).click();
	  	driver.findElement(By.name("Submit")).click();
	  	System.out.println("Your selected product was successfully added to cart");
	}
	
	
}
