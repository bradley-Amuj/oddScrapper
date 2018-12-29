import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class Betboss implements Runnable {
    WebDriver driver;
    ArrayList<WebElement>Container;
    WebElement Upcoming;

    public Betboss() {
        driver = new ChromeDriver();
        driver.get("https://betboss.co.ke/sportsbook/SOCCER/");

    }

    @Override
    public void run() {
       WebElement ontainer=  driver.findElement(By.xpath("//*[@id=\"1938351480\"]/div"));
        //Upcoming = Container.get(0);

        System.out.println(ontainer);

        driver.close();

    }
}
