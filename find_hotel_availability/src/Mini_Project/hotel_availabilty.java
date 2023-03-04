package Mini_Project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.testng.annotations.Test;

public class hotel_availabilty {

	public static void main(String[]args) throws InterruptedException, IOException{
		
		String date = "July 2022";
		String checkIn = "27";
		String checkOut = "28";
        String adults = "1";
        String children = "0";
        String rooms = "1";
		String month = "";
		
		File file = new File("chromedriver.exe");
		
		String absPath = file.getAbsolutePath();        //getting path of chromedriver from source folder
		    
		System.setProperty("webdriver.chrome.driver", absPath);
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();            //maximizing the window
		
		driver.get("https://www.trivago.in/");          //navigating to the application
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.id("input-auto-complete")).sendKeys("M u m b a i");           //finds the element of search box and enters city name
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				
		driver.findElement(By.xpath("//span[text()='City · Maharashtra, India']")).click(); //selecting city from auto suggestion
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		while(1>0)                                                                         //selecting the appropriate calendar(July)
		{   
			String text = driver.findElement(By.xpath("//button[@class='cursor-auto font-bold']")).getText();
			
			if(text.equals(date))
			break;
			
		    driver.findElement(By.xpath("//button[@data-testid='calendar-button-next']")).click();
		}
		
		switch(date){
		case "January 2022":
			month = "01";
		    break;
		case "February 2022":
			month = "02";
			break;
		case "March 2022":
			month = "03";
			break;
		case "April 2022":
			month = "04";
			break;
		case "May 2022":
			month = "05";
			break;
		case "June 2022":
			month = "06";
			break;
		case "July 2022":
			month = "07";
			break;
		case "August 2022":
			month = "08";
			break;
		case "September 2022":
			month = "09";
			break;
		case "October 2022":
			month = "10";
			break;
		case "November 2022":
			month = "11";
			break;
		case "December 2022":
			month = "12";
			break;
			}
		
		driver.findElement(By.xpath("//time[@datetime='2022-" + month + "-" + checkIn + "']")).click();              //selecting check-in date from the calendar
		driver.findElement(By.xpath("//time[@datetime='2022-" + month + "-" + checkOut + "']")).click();             //selecting check-out date from the calendar
		
		driver.findElement(By.xpath("//input[@data-testid='adults-amount']")).sendKeys(Keys.BACK_SPACE,adults);      //Entering no of adults
		driver.findElement(By.xpath("//input[@data-testid='children-amount']")).sendKeys(Keys.BACK_SPACE,children);  //Entering no of children
		driver.findElement(By.xpath("//input[@data-testid='rooms-amount']")).sendKeys(Keys.BACK_SPACE,rooms);        //Entering no of rooms
			
		driver.findElement(By.xpath("//button[@data-testid='guest-selector-apply']")).click();    
		
		driver.findElement(By.xpath("//button[@data-testid='search-button']")).click();
			
		Thread.sleep(50000); //Note: The wait time is 50 seconds because the site is checking for human for 30 seconds and took 10 seconds for loading the contents
		
		Select select = new Select(driver.findElement(By.cssSelector("[data-testid='sorting-selector'] [id^='select']")));  //selects sorting option from drop down list
		select.selectByValue("3");
		
		Thread.sleep(8000);
		
		List<WebElement> hotel_list = driver.findElements(By.xpath("//button[@data-testid='item-name']"));  //declaring list to store the list of hotels
		List<WebElement> rents = driver.findElements(By.xpath("//p[@data-testid='recommended-price']"));    //list for storing rents of hotels
		
		Thread.sleep(5000);
		
		int i=0;
		for(WebElement webelements :hotel_list) {                             //prints the hotel name and appropriate rents
			String hotelDetails = "Hotel : " + webelements.getText()+ ", Rent : Rs." + rents.get(i).getText().substring(1) + "/-";
			System.out.println(hotelDetails);
			i++;
			}
		
		driver.quit();           //quits the driver

	}
	
}