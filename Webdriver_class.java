import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


 

public class Webdriver_class {

	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// open tatoc
		driver.get("http://10.0.1.86/tatoc");
		       System.out.println(driver.getTitle());
		   

		       
		       //start basic course
		       driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
		       //selecting green box in grid
      		   driver.findElement(By.className("greenbox")).click();		      
		
	   // box1 and box2
		   WebDriverWait wait=new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));
		 String actualAnswer=driver.findElement(By.id("answer")).getAttribute("class");
		 Boolean condition=true;
		 while(condition) {
			 driver.findElement(By.cssSelector("body>center>a:nth-child(7)")).click();
			 WebElement childDiv=driver.findElement(By.id("child"));
			 driver.switchTo().frame(childDiv);
		String expectedAnswer=driver.findElement(By.id("answer")).getAttribute("class");
		driver.switchTo().parentFrame();
		if(actualAnswer.equals(expectedAnswer)) {
			condition=false;
		 }
		   }
	driver.findElement(By.cssSelector("body>center>a:nth-child(9)")).click();
driver.switchTo().defaultContent();
WebElement from=driver.findElement(By.id("dragbox"));
WebElement to = driver.findElement(By.id("dropbox"));
Actions act=new Actions(driver);
act.dragAndDrop(from, to).build().perform();
driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();


String mainwindow = driver.getWindowHandle();
driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
for(String nextWindow : driver.getWindowHandles() )
{
	driver.switchTo().window(nextWindow);
}
driver.findElement(By.id("name")).sendKeys("hello");
driver.findElement(By.id("submit")).click();
for(String nextWindow : driver.getWindowHandles())
{
	driver.switchTo().window(mainwindow);
}
driver.findElement(By.xpath("/html/body/div/div[2]/a[2]")).click();

driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
String d=driver.findElement(By.id("token")).getText();
//int lengthOfToken=d.length();
//if(lengthOfToken>=4) {
//	driver.findElement(By.xpath("/html/body/div/div[2]/a[2]")).click();
//System.out.println("Finish");
//System.out.println(lengthOfToken);
//}
String[] tokenValue = d.split("\\s");//Value splitted into two [0]:Token: [1]: dfsffd23hj
String token = tokenValue[1]; 
Cookie cookie = new Cookie("Token", token);
driver.manage().addCookie(cookie);
driver.findElement(By.cssSelector("a[onclick*='gonext();'")).click();
System.out.println(cookie);
driver.quit();



}
}
