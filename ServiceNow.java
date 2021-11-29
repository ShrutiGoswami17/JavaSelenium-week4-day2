package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	/*
	 * Assignment 2: ServiceNow - Frames
----------------------------------
ServiceNow- 
Refer the attached document below for clarity to create your own instance( sign In credentials) to work with servicenow
Refer the attached document for the flow of the application to complete the testcase
Step1: Load ServiceNow application URL 
Step2: Enter username (Check for frame before entering the username)
Step3: Enter password 
Step4: Click Login
Step5: Search “incident “ Filter Navigator
Step6: Click “All”
Step7: Click New button
Step8: Select a value for Caller and Enter value for short_description
Step9: Read the incident number and save it a variable
Step10: Click on Submit button
Step 11: Search the same incident number in the next search screen as below
Step12: Verify the incident is created successful and take snapshot of the created incident.
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://dev91729.service-now.com");
	
		Thread.sleep(8000);
		driver.switchTo().frame("gsft_main");
		System.out.println("frame has been selected");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Vijeet@0103");
		driver.findElement(By.id("sysverb_login")).click();
		
		
		driver.findElement(By.id("filter")).sendKeys("incident");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();
		
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		System.out.println("Child window  selected");
		Thread.sleep(5000);
		driver.findElement(By.className("glide_ref_item_link")).click();
		driver.switchTo().window(handlesList.get(0));
		System.out.println("parent window  selected");
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Added short Description");
		
		String incidentnum = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Search']/following::input)[1]")).sendKeys(incidentnum);
		driver.findElement(By.xpath("(//label[text()='Search']/following::input)[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String Incident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();

		if (Incident.equals(incidentnum)) {
			System.out.println("Incident Created Successfully");
		} else
			System.out.println("Incident Not Created");

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snaps/incident.png");
		FileUtils.copyFile(source, dest);
		
		driver.close();

	}
}

