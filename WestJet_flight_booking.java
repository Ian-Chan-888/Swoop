
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WestJet_flightbooking {

	
	
	
	public static void main(String[] args) throws InterruptedException {

	
	
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\eclipse\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.westjet.com/en-ca/book-trip/flight");
		driver.manage().window().maximize();//max browser window
		
		
		//selecting "From" Field
		try {
			
			WebElement From=driver.findElement(By.xpath("//input[@id='origin-search']"));		
			From.click();
			
			//clear "From" Field
			driver.findElement(By.xpath("//form[@id='book-flight-form']//div[@class='col-xs-12 col-smv-6 col-lgv-12 wrapper airport origin']//i[@class='stamp remove-sign']")).click();
			
			From.sendKeys("vA");
			From.sendKeys(Keys.ARROW_DOWN);// toggles to Vancouver,BC
			From.sendKeys(Keys.ENTER);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//selecting "To" Field
		
		try {
			WebElement To=driver.findElement(By.xpath("//input[@id='destination-search']"));
			//clear "To" field
			driver.findElement(By.xpath("//form[@id='book-flight-form']//div[@class='col-xs-12 col-smv-6 col-lgv-12 wrapper airport destination']//button[@class='clear']")).click();
			
			To.sendKeys("london");
			To.sendKeys(Keys.ARROW_DOWN);// toggles to London, ON
			To.sendKeys(Keys.ARROW_DOWN);// toggles to London, United Kingdom
			To.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
			//increment Adults by 1
			driver.findElement(By.xpath("//div[@id='adult-stepper']//i[contains(@class,'stamp plus')]")).click();
			
			//increment Children by 1
			driver.findElement(By.xpath("//div[@id='children-stepper']//i[contains(@class,'stamp plus')]")).click();

		

		
		//Departure Date
		driver.findElement(By.xpath("//input[@id='depart']")).click();

		//Navigate Month
		while(!driver.findElement(By.cssSelector("span[class='dw-cal-month']")).getText().contains("December"))
			{
			Thread.sleep(2000);	
			driver.findElement(By.cssSelector("div[class='dw-cal-btn-txt mbsc-ic mbsc-ic-arrow-right5']")).click();
			}

		//Navigate Day
		
		//Grab common attribute//Put into list and iterate
		int count=driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).size();//This counts the number of elements found
		
		
			for(int i=0;i<count;i++)
				{
					
				String text;
				// To prevent stale emlement exception Line 95-100 using try catch with StaleElementReferenceException reference
				try {
					text = driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).getText();
				} catch (StaleElementReferenceException e) {
					
					text = driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).getText();
				}
				
				
					if(text.equalsIgnoreCase("25"))
					{
						driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).click();
						break;
					}
					
					
}
			//clicking on the submit button
			driver.findElement(By.xpath("//div[@id='desktop-submit']//div//input")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	//  wait for page to load.
			
			
			//Validating Origin location	
			String origin;
			
			try { // from lines 119 to 121 using try catch to elmiate state element exception
				origin = driver.findElement(By.cssSelector("strong[class='origin']")).getText();
			} catch (StaleElementReferenceException e) {
				// TODO Auto-generated catch block
				origin = driver.findElement(By.cssSelector("strong[class='origin']")).getText();
			}
			
			
			if (!origin.equalsIgnoreCase("Vancouver (YVR)"))
					{
				System.out.println("Origin of Vancouver (YVR) FAILED");
				System.out.println("Instead got " + origin);
					}
			else
			{
				System.out.println("Origin of Vancouver (YVR) PASSED");

			}
			
			
			
			//Validating destination location	
			String destination;
						
				destination = driver.findElement(By.cssSelector("strong[class='destination']")).getText();
				
			
			if (!destination.equalsIgnoreCase("London (Gatwick) (LGW)"))
					{
				System.out.println("Destination of London (Gatwick) (LGW) FAILED");
				System.out.println("Instead got " + destination);
					}
			else
			{
				System.out.println("Destination of London (Gatwick) (LGW) PASSED");

			}
			
			
			
			//Validating Start Date location	
			String sdate;
						
			sdate = driver.findElement(By.xpath("//*[@id=\"main\"]/div[4]/div[1]/div/div/div[1]/div[2]/div/span/strong/span")).getText();
						
			if (!sdate.contains("Dec. 25"))
					{
				System.out.println("Start Date Validation FAILED: Expected Dec. 25");
				System.out.println("Instead got " + sdate);
					}
			else
			{
				System.out.println("Start Date Validation PASSED = " + sdate);

			}
			

			//Validating Number of Audults	
			String Adults;
						
			Adults = driver.findElement(By.xpath("//*[@id=\"main\"]/div[4]/div[1]/div/div/div[1]/div[3]/div/span[1]/strong")).getText();
			
			
			if (!Adults.contains("2"))
					{
				System.out.println("Number of Audults Validation FAILED Expected '2'");
				System.out.println("Instead got " + Adults);
					}
			else
			{
				System.out.println("Number of Audults Validation PASSED = " + Adults);

			}
			
			
			//Validating Number of Children	
			String Children;
						
			Children = driver.findElement(By.xpath("//*[@id=\"main\"]/div[4]/div[1]/div/div/div[1]/div[3]/div/span[2]/strong")).getText();
			
			
			if (!Children.contains("1"))
					{
				System.out.println("Number of Children Validation FAILED = " + Children);
					}
			else
			{
				System.out.println("Number of Children Validation PASSED= " + Children);

			}
				
			
			
			
			
			
		
			
			System.out.println("End Program");
			
}

}