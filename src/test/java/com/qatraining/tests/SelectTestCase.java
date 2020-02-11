package com.qatraining.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectTestCase {
	public static WebDriver driver;
	
	@Test
	public void faceBookPostTest() throws Exception {
		WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		// Opening the browser
		driver.get("https://google.com");

		// GoogleSearch bar xpath
		WebElement quotes = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
		quotes.sendKeys("martin luther king quotes");
		
		Thread.sleep(2000);
		// Selecting the first quote from the dropdown
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='erkvQe']/li"));
		
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getText());
			if(list.get(i).getText().contains("martin luther king quotes on politics")) {
				list.get(i).click();
				break;
			}
		}
		System.out.println("Total No of suggestions: " + list.size());

		driver.findElement(By.xpath("//h3[contains(text(), 'Martin Luther King, Jr. Quotes About Politics | A-Z Quotes')]")).click();
		
		WebElement bestQuote = driver.findElement(By.xpath("//div[@data-id='159019' and @class='wrap-block']/p/a"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", bestQuote);
		System.out.println(bestQuote.getText());
		String bestQuoteText = bestQuote.getText();
		Thread.sleep(5000);
		
		String facebookWindow = driver.getCurrentUrl();
		
		driver.get("https://facebook.com");
		WebElement userName = driver.findElement(By.xpath("//input[@type='email']"));
		userName.sendKeys("8790077141");
		
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("khaja1990@");
		
		WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Log In']"));
		loginBtn.submit();
		
		Thread.sleep(10000);
		System.out.println("logged in successfully");
        
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[starts-with(@id, 'placeholder-')]"))).click();
		
		//WebElement fbStatus = driver.findElement(By.xpath("//div[starts-with(@id, 'placeholder-')]"));
		//jse.executeScript("arguments[0].scrollIntoView();", fbStatus);
		//fbStatus.sendKeys(bestQuoteText);
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='placeholder-econk']"))).sendKeys(bestQuoteText);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='react-composer-post-button']"))).click();
		//System.out.println("is this working:" + postBtn);
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
