package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RightClickOnMenuDemo {
	WebDriver driver;

	String URL = "http://medialize.github.io/jQuery-contextMenu/demo.html";

	@BeforeClass
    public void Setup() {
		 System.setProperty("webdriver.firefox.marionette", "./server/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void f() {
		driver.navigate().to(URL);
		By locator = By.cssSelector(".document .context-menu-one.btn.btn-neutral");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 
		WebElement element=driver.findElement(locator);
		rightClick(element);
		//WebElement elementEdit =driver.findElement(By.cssSelector(".context-menu-list.context-menu-root>li"));
		WebElement elementEdit =driver.findElement(By.cssSelector(".context-menu-list.context-menu-root>li"));

		elementEdit.click();
		Alert alert=driver.switchTo().alert();
		String textEdit = alert.getText();
		Assert.assertEquals(textEdit, "clicked: edit", "Failed to click on Edit link");
	}
	

	public  void rightClick(WebElement element) {
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();

			System.out.println("Sucessfully Right clicked on the element");
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

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
