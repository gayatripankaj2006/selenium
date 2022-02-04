package newpackage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

//program to login to champs
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkValidator {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\chromedriver.exe");
		WebDriver driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		//String homePage="https://champsonline.co.uk/app/site/login";
		String homePage="http://aws.ittrpdemo.com/";
		driver.get(homePage);
		WebElement stafflink;
		stafflink=driver.findElement(By.xpath("//*[@id=\"header-part\"]/div[1]/div/div/div[2]/div/div[2]/ul/li[1]/a"));
		stafflink.click();
		System.out.println(stafflink);
		WebElement usr= driver.findElement(By.id("form-username"));
		usr.sendKeys("shriramrbisen@gmail.com");
		WebElement pass= driver.findElement(By.id("form-password"));
		pass.sendKeys("Shriram#1991");	
		WebElement btnsub= driver.findElement(By.id("submit"));
		btnsub.click();
		/* Code for broken links*/
		String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Iterator <WebElement> it = links.iterator();
        while(it.hasNext()){
            url = it.next().getAttribute("href");
            System.out.println(url);
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
            	continue;
            }
            if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }
                    
            }
            catch (MalformedURLException e) {
                
                e.printStackTrace();
            } catch (IOException e) {
               
                e.printStackTrace();
            }
            
        }
		
		 
		//driver.findElement(By.id("buttoncheck")).click();
	}

}
