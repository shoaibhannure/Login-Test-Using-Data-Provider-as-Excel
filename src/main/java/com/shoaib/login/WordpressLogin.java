package com.shoaib.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordpressLogin {

	@Test(dataProvider="wordpressData")
	public void loginToWordpress(String username, String Password) throws InterruptedException {
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\SHOAIB HANNURE\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("http://demosite.center/wordpress/wp-login.php");

		driver.findElement(By.xpath("//input[@id='user_login']")).clear();
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(username);
		
		driver.findElement(By.xpath("//input[@id='user_pass']")).clear();
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys(Password);
		
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		
		Thread.sleep(1000);

		System.out.println(driver.getTitle());
		
		driver.quit();
	}
	//Create a Method which starts with Public and return type must be Object, two dimensional Array.
	//Return Type should be two dimensional Array. Object is data Type
	
	@DataProvider(name="wordpressData")
	public Object[][] passData(){
		
		//Create two dimensional Object Array. Object is data Type. Object will store any type of data Type.
		Object[][] data= new Object[3][2];
		
		data[0][0]="admin1";
		data[0][1]="demo1";
		
		data[1][0]="admin";
		data[1][1]="demo123";
		return data;
	}

}
