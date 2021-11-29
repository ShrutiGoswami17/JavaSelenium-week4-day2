package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		//2) Mouseover on Brands and Search L'Oreal Paris
		WebElement element = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();
		Thread.sleep(3000);
		
		//3) Click L'Oreal Paris
		driver.findElement(By.xpath("//img[contains(@src,'lorealparis')]")).click();
		String pageTitle = driver.getTitle();
		
		//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		if (pageTitle.contains("L'Oreal Paris")) {
			System.out.println(" Correct Page");
		}
		
		//5) Click sort By and select customer top rated
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]")).click();
		
		//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.id("category")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[text()='hair'])[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Shampoo")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		
		//7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[@for='checkbox_Color Protection_10764']//div)[2]")).click();
		
		
		//8)check whether the Filter is applied with Shampoo
		String text = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		if (text.contains("Color Protection")) {
			System.out.println("Filter is applied correctly");
		}
		
		//9) Click on Tresemme Pro Protect Sulphate Free Shampoo
		driver.findElement(By.xpath("//div[text()='Tresemme Pro Protect Sulphate Free Shampoo']")).click();

		Set<String> handles1 = driver.getWindowHandles();
		List<String> handlesList1 = new ArrayList<String>(handles1);
		System.out.println(handlesList1.size());
		driver.switchTo().window(handlesList1.get(3));
		
		//10) GO to the new window and select size as 580ml
		driver.findElement(By.xpath("//span[text()='580ml']")).click();
		
		//11) Print the MRP of the product
		System.out.println("MRP of the product:"+driver.findElement(By.className("css-12x6n3h")).getText());
		
		//12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(5000);
		
		//13) Go to Shopping Bag 
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		
		//14) Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='name medium-strong']/following-sibling::div"))
				.getText();

		System.out.println("The Grand Total " + grandTotal);
		
		//15) Click Proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		
		//16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//17) Check if this grand total is the same in step 14
		String finalTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText();
		System.out.println(finalTotal);

		if (grandTotal.equals(finalTotal)) {
			System.out.println("Grand Total is same");
		}

		driver.quit();

	}

}