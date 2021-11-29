package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonWindowHandle {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.amazon.in/s?k=Books&ref=nb_sb_noss_2");
		System.out.println("Title of first window"+driver.getTitle());
		driver.findElement(By.xpath("//span[contains(text(),'Fingerprint')]")).click();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("number of window handles:"+allWindowHandles.size());
		
		List<String> allHandles=new ArrayList<String>(allWindowHandles);
		driver.switchTo().window(allHandles.get(1));
		System.out.println("Title of second window"+driver.getTitle());
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("snaps/snap1.png");
		FileUtils.copyFile(source, dest);
		
		driver.quit();
		
		

}
}
