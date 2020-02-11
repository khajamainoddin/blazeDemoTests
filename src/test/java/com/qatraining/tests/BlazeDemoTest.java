package com.qatraining.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.qa.utils.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BlazeDemoTest {
	public static WebDriver driver;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    public static String creditLastDigits;
    public static String lastDigits;
    
	@Test(priority=1)
	public void blazeDemoPageTest() throws Exception {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Opening the browser
		driver.get("http://www.blazedemo.com/");
		
		WebElement departureCity = driver.findElement(By.xpath("//*[@name='fromPort']"));
		Select depCity = new Select(departureCity);
		depCity.selectByVisibleText("Boston");
		
		WebElement destnCity = driver.findElement(By.xpath("//*[@name='toPort']"));
		Select destinaCity = new Select(destnCity);
		destinaCity.selectByVisibleText("New York");
		
		WebElement findFlightBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		findFlightBtn.click();
		
		WebElement flightChoose = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[contains(text(), '200')]//parent::tr/td[1]"));
		flightChoose.click();
		
		
	}
	
	@Test(priority=2)
	 public void ReadData() throws Exception
	 {
	     // Import excel sheet.
	     File src=new File("/home/khaja/Documents/maven_Projects/ClassRoomTraining/src/test/resources/excel/testData.xlsx");
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	     // Load he workbook.
	    workbook = new XSSFWorkbook(finput);
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheetAt(1);
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	         // Import data for Name.
	         cell = sheet.getRow(i).getCell(0);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement customerName = driver.findElement(By.xpath("//input[@id='inputName']"));
	         customerName.sendKeys(cell.getStringCellValue());
	         
	         // Import data for Address.
	         cell = sheet.getRow(i).getCell(1);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement customerAddress = driver.findElement(By.xpath("//input[@id='address']"));
	         customerAddress.sendKeys(cell.getStringCellValue());
	         
	         //Import data for City
	         cell = sheet.getRow(i).getCell(2);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement customerCity = driver.findElement(By.xpath("//input[@id='city']"));
	         customerCity.sendKeys(cell.getStringCellValue());
	         
	         
	         //Import data for State
	         cell = sheet.getRow(i).getCell(3);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement customerState = driver.findElement(By.xpath("//input[@id='state']"));
	         customerState.sendKeys(cell.getStringCellValue());
	         
	         //Import data for postalcode
	         cell = sheet.getRow(i).getCell(4);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement postalcode = driver.findElement(By.xpath("//input[@id='zipCode']"));
	         postalcode.sendKeys(cell.getStringCellValue());
	         
	         //Import data for CardType
	         cell = sheet.getRow(i).getCell(5);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement cardType = driver.findElement(By.xpath("//select[@id='cardType']"));
	         Select cardSelection = new Select(cardType);
	         cardSelection.selectByVisibleText(cell.getStringCellValue());
	         
	         //Import data for cardNumber
	         cell = sheet.getRow(i).getCell(6);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement creditCardNumber = driver.findElement(By.xpath("//input[@id='creditCardNumber']"));
	         String creditLastDigits = cell.getStringCellValue().toString().substring(12, 16);
	         
	         creditCardNumber.sendKeys(cell.getStringCellValue());
	         
	         cell = sheet.getRow(i).getCell(7);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement cardMonth = driver.findElement(By.xpath("//input[@id='creditCardMonth']"));
	         cardMonth.sendKeys(cell.getStringCellValue());
	         
	         cell = sheet.getRow(i).getCell(8);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement cardYear = driver.findElement(By.xpath("//input[@id='creditCardYear']"));
	         cardYear.sendKeys(cell.getStringCellValue());
	         
	         cell = sheet.getRow(i).getCell(9);
	         cell.setCellType(Cell.CELL_TYPE_STRING);
	         WebElement nameOnCard = driver.findElement(By.xpath("//input[@id='nameOnCard']"));
	         nameOnCard.sendKeys(cell.getStringCellValue());
	         
	         // Write data in the excel.
//	       FileOutputStream foutput=new FileOutputStream(src);
//	        // Specify the message needs to be written.
//	        String message = "Data Imported Successfully.";
//	         
//	        // Create cell where data needs to be written.
//	        sheet.getRow(i).createCell(3).setCellValue(message);
//	          
//	        // Specify the file in which data needs to be written.
//	        FileOutputStream fileOutput = new FileOutputStream(src);
//	         
//	        // finally write content
//
//	        workbook.write(fileOutput);
//	         
//	         // close the file
//	        fileOutput.close();
	         
	             
	     }
	     
	     WebElement purchaseFlightBtn = driver.findElement(By.xpath("//input[@class='btn btn-primary' and @value='Purchase Flight']"));
	     purchaseFlightBtn.click();
	     
	 }
//	@Test(dataProviderClass=TestUtil.class,dataProvider="dp", priority=2)
//	public void flightChoosingTest(Hashtable<String,String> data) throws Exception {
//		if(!data.get("runmode").equals("Y")){
//			
//			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
//		}
//		
//		WebElement flightChoose = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[contains(text(), '200')]//parent::tr/td[1]"));
//		flightChoose.click();
//		
//		WebElement customerName = driver.findElement(By.xpath("//input[@id='inputName']"));
//		data.get(customerName);
//	}
	
	@Test(priority=3)
	public void confirmationPageTest() {
		WebElement cardLastDigits = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[4]/td[2]"));
		String lastDigits = cardLastDigits.getText();
		System.out.println(lastDigits);
		
	}
	
	@Test(priority=4)
	public void numberverificationTest() throws Exception {
		System.out.println(creditLastDigits);
		if(creditLastDigits.toString().substring(12, 16) == lastDigits.substring(12, 16)) {
		   System.out.println("cards are same");
		   
		}else {
			System.out.println("cards are not same.");
		}
		Thread.sleep(6000);
	}
	@AfterClass
	public void tearDown() {
		driver.quit();

	}
			
}
