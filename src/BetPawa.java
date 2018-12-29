import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BetPawa implements Runnable {
    WebDriver betpawaDriver;
    ArrayList<WebElement>block_event;
    ArrayList<WebElement>selections;
    ArrayList<WebElement>eventList;
    ArrayList<Bet>TeamData = new ArrayList<>();

    public BetPawa() {
        betpawaDriver = new ChromeDriver();

        betpawaDriver.get("https://www.betpawa.co.ke/upcoming");
    }

    @Override
    public void run() {
        long lastHeight = (long) ((JavascriptExecutor)betpawaDriver).executeScript("return document.body.scrollHeight");
        long i = 0;


        while (i<=lastHeight) {

            block_event = (ArrayList<WebElement>) betpawaDriver.findElements(By.cssSelector(".block.event"));
               String string = String.format("window.scrollTo(0,%s)",i);
              ((JavascriptExecutor) betpawaDriver)
                      .executeScript(string);
            i += 150;


        }

//        ((JavascriptExecutor)betpawaDriver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
//        block_event = (ArrayList<WebElement>) betpawaDriver.findElements(By.cssSelector(".block.event"));






        eventList = (ArrayList<WebElement>)betpawaDriver.findElements(By.cssSelector(".events-container.prematch"));

        for(WebElement team: eventList){
            Bet Team =  new Bet();



            selections = (ArrayList<WebElement>)team.findElements(By.cssSelector(".event-bet"));

            String [] teams =team.findElement(By.cssSelector(".general-live-container.first > h3:nth-child(1)")).getText().split("-");
            Team.setSite("BetPawa");
            Team.setHome_team(teams[0]);
            Team.setAway_team(teams[1]);

            for(int x = 0;x<=selections.size();x++){
               switch (x){
                   case 0:
                       Team.setHome_win(selections.get(0).findElement(By.cssSelector(".event-odds")).getText());
                       break;
                   case 1:
                       Team.setDraw(selections.get(1).findElement(By.cssSelector(".event-odds")).getText());

                       break;
                   case 2:
                       Team.setAway_win(selections.get(2).findElement(By.cssSelector(".event-odds")).getText());


               }

            }


            TeamData.add(Team);
        }





        betpawaDriver.close();

    }

}
