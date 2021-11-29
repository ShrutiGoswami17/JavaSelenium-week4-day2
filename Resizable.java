package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		
		
		Actions builder = new Actions(driver);
		
		builder.moveToElement(element).perform();
		
		Thread.sleep(3000);
		builder.dragAndDropBy(element,100,30).perform();
		
		driver.close();
		
	}

}
