import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class odibets implements Runnable {
    WebDriver driver;

    public odibets() {
        driver = new ChromeDriver();
        driver.get("https://odibets.com/home?tab=today");


    }

    @Override
    public void run() {



    }
}
