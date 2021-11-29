package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.xpath("//div[@id='draggable']/p"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(element,20,20).perform();
		
		driver.close();
		
	}

}
