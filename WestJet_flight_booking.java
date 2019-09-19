
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WestJet_flightbooking {

	
	
	
	public static void main(String[] args) throws InterruptedException {

	
	
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\eclipse\\chromedriver.exe"); //Your driver location will be different
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
					String text=driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).getText();  //Known issue here where sometimes get : Stale Element (see Readme.md)
					if(text.equalsIgnoreCase("25"))
					{
						driver.findElements(By.cssSelector("div[class='dw-cal-day-fg']")).get(i).click();
						break;
					}
					
					
}
			//clicking on the submit button
			driver.findElement(By.xpath("//div[@id='desktop-submit']//div//input")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	//  wait for page to load.
			
			
			// Validating entries by comparing url to known values.
			System.out.println(driver.getCurrentUrl());
			String url=driver.getCurrentUrl();
			
			if(!url.contains("https://www.westjet.com/shop/?lang=en&type=search&origin=YVR&destination=LGW&adults=2&children=1&infants=0&outboundDate=2019-12-25&returnDate=&companionvoucher=false&iswestjetdollars=false&promo=&currency=CAD&caller=https%3A%2F%2Fwww.westjet.com%2Fen-ca%2Fbook-trip%2Fflight"))
			{
				System.out.println("Failed: Selections do not match");
				
			}
			else
			{
				System.out.println("Passed: All selections match");
			}
			
			System.out.println("End Program");
			
}

}