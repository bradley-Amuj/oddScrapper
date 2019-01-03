import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BetPawa implements Runnable {
    private long new_height;
    WebDriver driver;
    // contains the list of teams playing not necessarily on that data
    ArrayList<Bet>teamsList = new ArrayList<>();
    ArrayList<WebElement>block_event;
    ArrayList<WebElement>selections;
    ArrayList<WebElement>eventList;
    // contains list of teams playing on that day
    ArrayList<Bet>TeamData = new ArrayList<>();

    public BetPawa(WebDriver driver) {
        driver.get("https://www.betpawa.co.ke/upcoming");
        this.driver = driver;


    }

    @Override
    public void run() {
        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");
        LocalDate localDate = LocalDate.now();
        String currentdate = dtf.format(localDate);

        while (true) {
            block_event = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".block.event"));
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight);");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new_height = (long) ((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");
            if (new_height == lastHeight) {
                break;

            }
            else{
            lastHeight = new_height;

        }}


        eventList = (ArrayList<WebElement>)driver.findElements(By.cssSelector(".events-container.prematch"));

        for(WebElement team: eventList){
            Bet Team =  new Bet();

            selections = (ArrayList<WebElement>)team.findElements(By.cssSelector(".event-bet"));
            String date = team.findElement(By.cssSelector(".date-minutes")).getText();
            String [] teams =team.findElement(By.cssSelector(".general-live-container.first > h3:nth-child(1)")).getText().split("-");
            Team.setSite("BetPawa");
            Team.setHome_team(teams[0]);
            Team.setAway_team(teams[1]);
            Team.setDate(date.split(" ")[1]);



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
            teamsList.add(Team);
        }




        for(Bet team:teamsList){
            if(team.getDate().equals(currentdate)){
                TeamData.add(team);
            }
        }

        if(TeamData.isEmpty()){
            System.out.println("No current events today");
        }


        driver.close();

    }

}
