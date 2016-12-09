package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;


public class DoubleClickOnElement {
	WebDriver driver;
	
	@Test
	public void f() {
	  driver.navigate().to("http://api.jquery.com/dblclick/");
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.cssSelector("html>body>div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	  System.out.println("Hello q");
	  new Actions(driver).doubleClick(element).build().perform();
	//	doubleClick(element);
		System.out.println("Hello");
		WebElement ele = driver.findElement(By.cssSelector(".dbl"));
		if (ele.isDisplayed()) {
			System.out.println("Color Changed successfully after we double clicked");
		}

	  
  }
  @BeforeClass
  public void beforeClass() {
	 System.setProperty("webdriver.firefox.marionette", "./server/geckodriver.exe");
	  driver = new FirefoxDriver();
		driver.manage().window().maximize();
  }
 
 


@AfterClass
  public void afterClass() {
	  driver.quit();
  }

public void doubleClick(WebElement element) {
	  try {
			Actions action = new Actions(driver).doubleClick(element);
			action.build().perform();

			System.out.println("Double clicked the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	
}

}
