package com.shoaib.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shoaib.utility.ExcelDataConfig;

public class WordpressLoginExcel {
	WebDriver driver;

	@Test(dataProvider = "wordpressData")
	public void loginToWordpress(String username, String Password) throws InterruptedException {
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\SHOAIB HANNURE\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("http://demosite.center/wordpress/wp-login.php");

		driver.findElement(By.xpath("//input[@id='user_login']")).clear();
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(username);

		driver.findElement(By.xpath("//input[@id='user_pass']")).clear();
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys(Password);

		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();

		Thread.sleep(5000);

		// System.out.println(driver.getTitle());

		Assert.assertTrue(driver.getTitle().contains("Dashboard"), "Invalid Credentials");
		// In Assert statement third parameter will only appear if assertion is
		// fail

		// driver.quit(); //driver will not quit if assert is true so write it
		// in annotation After Method
	}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}
	// Create a Method which starts with Public and return type must be Object,
	// two dimensional Array.
	// Return Type should be two dimensional Array. Object is data Type

	@DataProvider(name = "wordpressData")
	public Object[][] passData() {

		ExcelDataConfig config = new ExcelDataConfig(
				"D:\\Login_TestNG_DataProvider_Excel\\TestData\\InputCredentials.xlsx");
		int rows = config.getRowCount(0);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0); // This is for Row
			data[i][1] = config.getData(0, i, 1); // This is for Column
		}
		return data;
	}

}
