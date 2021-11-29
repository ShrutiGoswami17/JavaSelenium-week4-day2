package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment4 {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		WebElement element = driver.findElement(By.tagName("iframe"));
		File source = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("snaps/frameScreen.png");
		FileUtils.copyFile(source, dest);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("No.of Frames " + frames.size());

	}

}