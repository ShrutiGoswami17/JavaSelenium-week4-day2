package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		
		//1.Load the uRL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		//2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//3.Get the price of the first product
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("The Price of the first Product " + price);
		
		//4. Print the number of customer ratings for the first displayed product
		String ratings = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
		System.out.println("No.of customer Ratings for the first Product " + ratings);
		
		//5. click on the stars 
		driver.findElement(By.xpath("//a[@class='a-popover-trigger a-declarative']//i")).click();
		
		Thread.sleep(5000);
		
		//6. Get the percentage of ratings for the 5 star.
		String percent = driver.findElement(By.xpath("(//a[contains(@title,'have 5 stars')])[3]")).getText();
		System.out.println("Percentage of ratings for the 5 star: " + percent);
		
		//7. Click the first text link of the first image
		driver.findElement(By.xpath("(//a[@class = 'a-link-normal a-text-normal'])[1]")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		
		//8. Take a screen shot of the product displayed
		WebElement phoneImage = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		File source = phoneImage.getScreenshotAs(OutputType.FILE);
		File dest = new File("snaps/Amazonphone.png");
		FileUtils.copyFile(source, dest);
		Thread.sleep(5000);
		
		//9. Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
		
		//10. Get the cart subtotal and verify if it is correct.
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']")).click();
		String subTotal = driver.findElement(By.xpath("//span[contains(@id,'sc-subtotal-amount-activecart')]")).getText();
		System.out.println("SubTotal:"+subTotal);
		subTotal=subTotal.replaceAll(".","");
		if (price.contains(subTotal)) {
			System.out.println("Validation Complete");
		} else
			System.out.println("Validation Failed");

	
	
	driver.quit();
	}
}