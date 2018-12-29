import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class EliteBetThread implements Runnable {

   private WebDriver driver;
    WebElement allElements;
   private ArrayList<WebElement>teamList;
    List<WebElement>ul;


    public EliteBetThread() {
        driver = new ChromeDriver();
        driver.get("https://m.elitebetkenya.com/sportsbook/prelive/today");

    }

    @Override
    public void run() {


        //allElements = driver.findElement(By.cssSelector(".container"));
        //System.out.println(driver.findElement(By.cssSelector(".border.top.link")));

        driver.close();

    }
}
