package seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Main {
	
//selenium documentation https://www.selenium.dev/documentation/webdriver/waits/
//effective xpath https://www.toolsqa.com/selenium-webdriver/write-effective-xpaths/
	public static void main(String[] args) throws InterruptedException, IOException {
        String TESTBUY2 = "https://www.target.com/p/chuckle-38-roar-pop-it-the-original-take-anywhere-bubble-popping-fidget-and-sensory-game/-/A-76151619#lnk=sametab";
        int seconds = 1;
        DrOptions drOptions = new DrOptions(); 

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\logfi\\eclipse-workspace\\seleniumProject\\bin\\lib\\chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeOptions options = new ChromeOptions();
        /**  CURRENT WORKAROUND FOR
         *  CHROME IS BEING CONTROLLED BY AUTOMATED TEST SOFTWARE 'OPTIONS' */        
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("chrome.switches", "--disable-extensions");
        //options.addArguments("--headless");
        
        //* When setting up profile copy directory User Data and paste copy on desktop
        // Set to this directory and choose a new non default profiles**/
        options.addArguments("--user-data-dir=C:\\Users\\logfi\\OneDrive\\Desktop\\User Data\\","--profile-directory=Profile 1");
        options.addArguments("no-sandbox");
        String message = "print";
        WebDriver driver = new ChromeDriver(options);
        
        // uses what is entered in userCVV text file, Will be used for saved Target CC
        //change file settings to your path
        File userFile1 = new File("C:\\Users\\logfi\\eclipse-workspace\\seleniumProject\\userCVV.txt");
        File userFileURL = new File("C:\\Users\\logfi\\eclipse-workspace\\seleniumProject\\userURL.txt");
        Scanner myReader = new Scanner(userFile1);
        Scanner urlReader = new Scanner(userFileURL);

        String data = myReader.nextLine();    
        String fileURL = urlReader.nextLine();
        driver.get(fileURL);
          try {  
        	  boolean displayed = false;
              int timesTried = 0;
              Thread.sleep(10000);
              do {
                  Thread.sleep(100);
                  driver.navigate().refresh();
                  System.out.println("Refreshing page");
                  timesTried++;
                  //Thread.sleep(400);
                  System.out.println("Times tried looking for element: " + timesTried);
              }
        	  while (ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'addToCartButton')]"))==null);{
                  System.out.println("Add to cart attempted ");
                  Thread.sleep(1000);
                  System.out.println("Times tried looking for element: " + timesTried);
                  WebElement waitForShipIt = new WebDriverWait(driver, Duration.ofSeconds(15))
                          .until(elementToBeClickable(By.xpath("//button[contains(@id, 'addT')]")));
                  driver.findElement(By.xpath("//button[contains(@id, 'addT')]")).click();
                  System.out.println("Product added");
                  //driver.wait(40);
        	  }        	          	
  	
        	WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(4))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id, 'addT')]")));                               
            driver.findElement(By.xpath("//button[contains(@id, 'addT')]")).click();
            System.out.println("Add to cart complete.");            	

            //ADD IF WHILE STATEMENT BELOW
            /**
            WebElement alreadyInCart = new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-test='custom-quantity-picker')]")));                               
            Boolean isPresent = driver.findElements(By.xpath("//*[contains(@id='select')]")).size() > 0;
            System.out.println(isPresent);  
            driver.findElement(By.xpath("//span[contains(@data-icon-name='CommerceCartLine')]")).click();
           */

            //System.out.println(isPresent);
            WebElement viewCart = new WebDriverWait(driver, Duration.ofSeconds(15))
                   .until(ExpectedConditions.elementToBeClickable(By.linkText("View cart & check out")));                               
            driver.findElement(By.linkText("View cart & check out")).click();
            System.out.println("View cart clicked.");

            /**
            	while(!driver.findElement(By.xpath("//button[contains(@class, 'IconButton')]")).isEnabled()) {
            		Actions action = new Actions(driver);
            		//WebElement closeNavCircle = new WebDriverWait(driver, Duration.ofSeconds(15))
                            //.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-icon-name, 'NavigationCloseCircle')]")));                                      
            		driver.findElement(By.xpath("//button[contains(@class, 'IconButton')]")).click();	
            		Thread.sleep(70);
            		// send keys alt + leftKey
            		action.keyDown(Keys.ALT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT);
            		Thread.sleep(70);
            		//send keys alt + rightKey
            		action.keyDown(Keys.ALT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT);
            		Thread.sleep(40);
            	}
            */
        	WebElement checkOut = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-test, 'checkout-button')]")));	
        	driver.findElement(By.xpath("//button[contains(@data-test, 'checkout-button')]")).click();
        	System.out.println("Check out clicked.");        		
        	WebElement placeOrder = new WebDriverWait(driver, Duration.ofSeconds(15))
                   .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-test, 'placeOrderButton')]")));	
        	System.out.println("Place order found.");
        	driver.findElement(By.xpath("//button[contains(@data-test, 'placeOrderButton')]")).click();
        	System.out.println("Place order clicked.");
        		
        	WebElement enterCVV = new WebDriverWait(driver, Duration.ofSeconds(15))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'cvv')]")));	
        	driver.findElement(By.xpath("//input[contains(@id, 'cvv')]")).click();
        	driver.findElement(By.xpath("//input[contains(@id, 'cvv')]")).sendKeys(data);             		
        	System.out.println("CVV Entered into box.");
        	driver.findElement(By.xpath("//button[contains(@data-test, 'confirm-button')]")).click();        		
        	//create if driver.findElement('sign into you Target account' is visible 	
            	
        } catch (NoSuchElementException b) {
            b.printStackTrace();
        }        
        myReader.close();
        try {

         
        } finally {
            System.out.println("All lines complete");
            return;
            //driver.quit();
        }
    

	}

}
