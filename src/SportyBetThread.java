import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class SportyBetThread  implements Runnable{
    WebDriver driver;
    ArrayList<WebElement>leagueRows;
    ArrayList<WebElement>teamList;
    ArrayList<Bet>TeamsData = new ArrayList<>();
    ArrayList<WebElement>outcomes;

    public SportyBetThread() {
        driver = new ChromeDriver();
        driver.get("https://www.sportybet.com/ke/sport/football/today");

    }

    @Override
    public void run() {

        long lastHeight = (long) ((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");
        long i = 0;
        while (i<=lastHeight) {

            // leagueRows = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".match-league-wrap"));

            teamList = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".m-table-row.m-content-row.match-row"));
               String string = String.format("window.scrollTo(0,%s)",i);
              ((JavascriptExecutor) driver)
                      .executeScript(string);
            i += 150;

        }




        for(WebElement item: teamList){

            Bet team = new Bet();
            outcomes = (ArrayList<WebElement>) item.findElements(By.cssSelector(".m-outcome"));


            team.setSite("SportyBet");
            team.setHome_team(item.findElement(By.cssSelector(".home-team")).getText());



            team.setAway_team(item.findElement(By.cssSelector(".away-team")).getText());
            team.setHome_win(outcomes.get(0).getText());
            team.setDraw(outcomes.get(1).getText());
            team.setAway_win(outcomes.get(2).getText());




            TeamsData.add(team);
        }


//        for(Bet details: TeamsData){
//
//            System.out.println(details.toString());
//        }





        driver.close();



    }
}

