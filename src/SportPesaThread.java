import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SportPesaThread implements Runnable {
    private WebDriver driver;
    private ArrayList<WebElement> row;
     ArrayList<WebElement>rows = new ArrayList<>();
    public ArrayList<Bet>teamsdata= new ArrayList<>();

    public SportPesaThread() {

        driver = new ChromeDriver();

        driver.get("https://www.sportpesa.co.ke/?sportId=1&section=today");

    }

    @Override
    public void run() {
        long lastHeight = (long) ((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");

            int i = 0;
            int times = 0;




        do {

            row = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".event-row-container.ng-scope"));

            WebElement element = driver.findElement(By.cssSelector(".paybillnumbers"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.ng-scope[translate='next']"))).click();
            times++;
            rows.addAll(row);
            }
            while(times <=6);

        System.out.println(rows.size());

        //System.out.println(driver.findElement(By.cssSelector(".betslip-row")).getLocation().getY());



//            do {
//
//
//                while (i <=driver.findElement(By.cssSelector(".betslip-row")).getLocation().getY()) {
//
//
//                    rows = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".event-row-container.ng-scope"));
//
//                    String string = String.format("window.scrollTo(0,%s)", i);
//                    ((JavascriptExecutor) driver)
//                            .executeScript(string);
//
//
//                    i += 100;
//
//                }
//                times++;
//                i = 0;
//                new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.ng-scope[translate='next']"))).click();
//            }while(times<=10);
//        System.out.println(rows.size());
//

            for (WebElement rowDetails : rows) {

                new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".event-market.market-3-way.market-selections-3")));
                ArrayList<WebElement> rowDetails2 = (ArrayList<WebElement>) rowDetails.findElements(By.cssSelector(".event-market.market-3-way.market-selections-3"));
                ArrayList<WebElement> teamDetails = (ArrayList<WebElement>) rowDetails2.get(0).findElements(By.cssSelector(".event-selection.ng-scope"));

                Bet teams = new Bet();
                for (int x = 0; x < 3; x++) {

                    switch (x) {

                        case 0:
                            teams.setHome_team(teamDetails.get(0).findElement(By.cssSelector(".event-text.ng-scope")).getText().toLowerCase());
                            teams.setHome_win(teamDetails.get(0).findElement(By.cssSelector(".ng-binding")).getText());

                            break;
                        case 1:
                            teams.setDraw(teamDetails.get(1).findElement(By.cssSelector(".ng-binding")).getText());

                            break;
                        case 2:
                            teams.setAway_team( teamDetails.get(2).findElement(By.cssSelector(".event-text.ng-scope")).getText().toLowerCase());
                            teams.setAway_win(teamDetails.get(2).findElement(By.cssSelector(".ng-binding")).getText());
                            teams.setSite(" SportPesa");
                            break;


                    }

                }
                teamsdata.add(teams);


            }

//        for(Bet item: teamsdata){
//            System.out.println(item.toString());
//        }




        driver.close();
    }
    }

