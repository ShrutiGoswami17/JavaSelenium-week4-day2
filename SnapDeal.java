package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		//2. Go to Mens Fashion
		WebElement mensFashion = driver.findElement(By.xpath("//div[@id='leftNavMenuRevamp']/div[1]/div[2]/ul[1]/li[7]/a[1]/span[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mensFashion).perform();
		mensFashion.click();
		
		//3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
		
		//4. Get the count of the sports shoes
		String total = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Number of Sport Shoes: " + total);
		
		//5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//6. Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		
		//7. Check if the items displayed are sorted correctly
		driver.findElement(By.xpath("(//ul[@class='sort-value'])/li[2]")).click();
		Thread.sleep(5000);
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> priceList = new ArrayList<Integer>();
		//boolean isSorted = false;

		for (int i = 0; i <price.size() ; i++) {
			String text = price.get(i).getText();
			String value = text.replaceAll("[^0-9]", "");
			int number = Integer.parseInt(value);
			priceList.add(number);

		}

		List<Integer> priceList1 = new ArrayList<Integer>(priceList);
		

		Collections.sort(priceList1);
		boolean sorted = priceList1.equals(priceList);

		if (sorted) 
			System.out.println("The price is sorted from Low to High");
		 else 
			System.out.println("The price is not sorted correctly");
		

		System.out.println(priceList);
		System.out.println(priceList1);
		
		//8.Select the price range (900-1200)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("2500");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(3000);
		
		//9.Filter with color Navy 
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		Thread.sleep(3000);
		
		//10 verify the all applied filters 
		String filter1 = driver.findElement(By.xpath("(//div[@class='navFiltersPill']/a)[1]")).getText();
		String filter2 = driver.findElement(By.xpath("(//div[@class='navFiltersPill']/a)[2]")).getText();
		System.out.println(filter1);
		System.out.println(filter2);

		if ((filter1.contains("Rs. 900 - Rs. 2500")) && (filter2.contains("Navy"))) {
			System.out.println("Filter is applied correctly");
		}
		
		//11. Mouse Hover on first resulting Training shoes
		WebElement firstElement = driver.findElement(By.xpath("//img[@class='product-image wooble']"));

		Actions builder1 = new Actions(driver);
		builder1.moveToElement(firstElement).perform();

		Thread.sleep(5000);
		
		//12. click QuickView button
		driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]")).click();
		Thread.sleep(5000);
		
		//13. Print the cost and the discount percentage
		
		
		String cost = driver.findElement(By.className("payBlkBig")).getText();
		//String discountPercent = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("The Cost the Shoe: " +cost);
		//System.out.println("The Discount Percentage of the shoe: "+discountPercent);
		
		
		//14. Take the snapshot of the shoes.
		WebElement element = driver.findElement(By.xpath("//img[@itemprop='image']"));
		File source = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("snaps/Snapdealshoe.png");
		FileUtils.copyFile(source, dest);
		driver.quit();
		
	}

}