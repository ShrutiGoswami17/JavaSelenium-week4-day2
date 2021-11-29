package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertAndFrames {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
		
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		driver.switchTo().alert().sendKeys("Shruti");
		driver.switchTo().alert().accept();
		String text = driver.findElement(By.id("demo")).getText();
		
		if(text.contains("Shruti"))
		{
			System.out.println("Name exists");
		}
		else
			System.out.println("Name does not exist");
		
		driver.close();
		
	}
}

