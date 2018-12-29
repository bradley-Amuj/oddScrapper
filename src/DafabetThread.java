import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DafabetThread implements Runnable {

    private WebDriver Dafadriver;
    private ArrayList<WebElement> eventsRows;
    private List<WebElement>eventContainers;
    private ArrayList<WebElement>teamDetails;


    public DafabetThread() {
        Dafadriver = new ChromeDriver();
        //Dafadriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Dafadriver.get("https://dafabet.co.ke/sports/foot");
    }

    @Override
    public void run() {
        long lastHeight = (long) ((JavascriptExecutor)Dafadriver).executeScript("return document.body.scrollHeight");
        System.out.println(lastHeight);


        long i = 0;

        Dafadriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        while (i<=1000) {
            eventsRows =(ArrayList<WebElement>)Dafadriver.findElements(By.cssSelector(".league-block.league"));

            String string = String.format("window.scrollTo(0,%s)",i);
            ((JavascriptExecutor) Dafadriver)
                    .executeScript(string);
            i += 200;

        }


        System.out.println(eventsRows.get(0).findElement(By.cssSelector(".league-heading")));








//        for(WebElement teamList: eventsRows){
//
//            System.out.println(teamList.getText());
//

//           eventContainers = (ArrayList<WebElement>)teamList.findElement(By.cssSelector(".events-container"));
//
//         for(WebElement team: teamDetails){
//               String homeTeam = team.findElement(By.cssSelector("outcome-desc.FOOT.marquee")).getText();
//               String homeWin = team.findElement(By.xpath("//*[@id=\"betapp\"]/div[2]/div/div[2]/div/div[2]/div[6]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div[4]/div[2]/div/div[6]/div/div[1]/div/div[2]/span[1]")).getText();

//               String draw = team.findElement(By.xpath("//*[@id=\"betapp\"]/div[2]/div/div[2]/div/div[2]/div[6]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div[4]/div[2]/div/div[6]/div/div[2]/div/div[2]/span[1]")).getText();
//
//               String awayTeam = team.findElement(By.xpath("//*[@id=\"betapp\"]/div[2]/div/div[2]/div/div[2]/div[6]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div[4]/div[2]/div/div[6]/div/div[3]/div/div[1]")).getText();
//               String awayWin = team.findElement(By.xpath("//*[@id=\"betapp\"]/div[2]/div/div[2]/div/div[2]/div[6]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div[4]/div[2]/div/div[6]/div/div[3]/div/div[2]/span[1]")).getText();
//
//               System.out.println("homeTeam:"+ homeTeam.concat(homeWin)+" \n "+
//               draw+" \n "+ "awayTeam:"+awayTeam.concat(awayWin));
//           }

//        }
        Dafadriver.close();
    }
}
