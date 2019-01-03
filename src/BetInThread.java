import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BetInThread implements Runnable{
    private WebDriver driver;
    private ArrayList<WebElement> rows = new ArrayList<>();
    ArrayList<Bet>teamsData = new ArrayList<>();

    public BetInThread(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://sports.betin.co.ke/mobile#/dailyBundle/soccer/1-1000");

    }

    @Override
    public void run() {


        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        long i = 0;

        while (i<=lastHeight) {
            rows = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".match-content.table-a.soccer"));

            String string = String.format("window.scrollTo(0,%s)",i);
            ((JavascriptExecutor)driver)
                    .executeScript(string);
            i += 200;
        }

        for (WebElement rowDetails : rows) {
            ArrayList<WebElement> teamDetails = (ArrayList<WebElement>) rowDetails.findElements(By.cssSelector(".match-content__row.table-f"));
            ArrayList<WebElement> betDetails = (ArrayList<WebElement>) rowDetails.findElements(By.cssSelector(".bets__item"));

            Bet Team = new Bet();

            for (int x = 0; x < teamDetails.size(); x++) {


                switch (x) {
                    case 0:
                        Team.setHome_team(teamDetails.get(x).getText().toLowerCase());
                        break;
                    case 1:
                        Team.setAway_team(teamDetails.get(x).getText().toLowerCase());
                        break;

                }


            }

            for (int y = 0; y < betDetails.size(); y++) {
                switch (y) {
                    case 0:
                        Team.setHome_win(betDetails.get(y).getText());
                        break;
                    case 1:
                        Team.setDraw(betDetails.get(y).getText());
                        break;
                    case 2:
                        Team.setAway_win(betDetails.get(y).getText());
                        Team.setSite("BetIn");
                        break;
                }


            }
            teamsData.add(Team);

        }

        driver.close();

    }
}
