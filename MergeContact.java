package week4.day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		
		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		
		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		
		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[@href='/crmsfa/control/mergeContactsForm']")).click();
		
		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		//System.out.println("number of window handles:"+allWindowHandles.size());
		
		List<String> allHandles=new ArrayList<String>(allWindowHandles);
		driver.switchTo().window(allHandles.get(1));
		
		
		Thread.sleep(5000);
		//8. Click on First Resulting Contact
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		
		driver.switchTo().window(allHandles.get(0));
		
		//9. Click on Widget of To Contact
		driver.findElement(By.xpath("//table[@name='ComboBox_partyIdTo']/following-sibling::a")).click();
		
		Set<String> Handles2 = driver.getWindowHandles();
		List<String> HandlesList2 = new ArrayList<String>(Handles2);
		driver.switchTo().window(HandlesList2.get(1));
		
		Thread.sleep(8000);
		// 10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]")).click();
		
		driver.switchTo().window(HandlesList2.get(0));
		
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
		// 12. Accept the Alert
		driver.switchTo().alert().accept();
		
		//13. Verify the title of the page
		System.out.println("Title of the page:"+driver.getTitle());
		
		driver.close();

	}
		
		

}




/*
 * //Pseudo Code
 
 
 *
 * 
 * 
 * 
 *
 * 
 * 
 */
