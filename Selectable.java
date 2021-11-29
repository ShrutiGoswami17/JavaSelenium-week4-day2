package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		
		driver.switchTo().frame(0);
		WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		item1.click();
		Thread.sleep(2000);
		WebElement item2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
		item2.click();
		Thread.sleep(2000);
		WebElement item5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		Actions builder = new Actions(driver);
		builder.click(item1).keyDown(Keys.CONTROL).click(item5).keyUp(Keys.CONTROL)
		.perform();
		
		Thread.sleep(2000);
		builder.clickAndHold(item1).moveToElement(item5).release().perform();
		
		driver.close();
		

	}

}