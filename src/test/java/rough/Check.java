package rough;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Check {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://app.hubspot.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		
		driver.findElement(By.id("username")).sendKeys("deep.gupta.6391@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Lovely@1234");
		driver.findElement(By.id("loginBtn")).click();
		
		Thread.sleep(10000);
		
		List<WebElement> listLinks=driver.findElements(By.xpath("//div[@class='desktop-nav-left-container']//a[@class='primary-link']"));
		System.out.println(listLinks.size());

		for(int i=1;i<listLinks.size();i++) {
			System.out.println(listLinks.get(i).getText().trim());
		}
		
		Thread.sleep(10000);
		driver.quit();
	}

}
