import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Bet> betIN_sporty = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "/Users/user/Desktop/Java/chromedriver");

        BetInThread betInThread = new BetInThread(new ChromeDriver());
        betInThread.run();

        SportyBetThread sportyBetThread = new SportyBetThread(new ChromeDriver());
        sportyBetThread.run();

       BetPawa betPawa = new BetPawa(new ChromeDriver());
       betPawa.run();

        betIN_sporty = getMatch(betInThread.teamsData, sportyBetThread.TeamsData);


        boolean positiveReturns = false;
        for (int a = 0; a < betPawa.TeamData.size(); a++) {

            for (int b = 0; b < betIN_sporty.size(); b++) {

                if (similarity(betPawa.TeamData.get(a).getHome_team(), betIN_sporty.get(b).getHome_team()) >= 0.6 && similarity(betPawa.TeamData.get(a).getAway_team(), betIN_sporty.get(b).getAway_team()) >= 0.6) {
                    System.out.println("Site:" + betPawa.TeamData.get(a).getSite() + " \n" + "Home Team: " + betPawa.TeamData.get(a).getHome_team() + ": " + betPawa.TeamData.get(a).getHome_win()
                            + " " + "Draw: " + betPawa.TeamData.get(a).getDraw() + " "
                            + "Away Team: " + betPawa.TeamData.get(a).getAway_team() + ": " + betPawa.TeamData.get(a).getAway_win() + " " + "\n" +
                            "Site:" + betIN_sporty.get(b).getSite() + "\n" + "Home Team: " + betIN_sporty.get(b).getHome_team() + ": " + betIN_sporty.get(b).getHome_win()
                            + " " + "Draw: " + betIN_sporty.get(b).getDraw() + " "
                            + "Away Team: " + betIN_sporty.get(b).getAway_team() + ": " + betIN_sporty.get(b).getAway_win());


                    float max_Homewin = Math.max(Float.parseFloat(betPawa.TeamData.get(a).getHome_win()), Float.parseFloat(betIN_sporty.get(b).getHome_win()));
                    float max_draw = Math.max(Float.parseFloat(betPawa.TeamData.get(a).getDraw()), Float.parseFloat(betIN_sporty.get(b).getDraw()));
                    float max_AwayWin = Math.max(Float.parseFloat(betPawa.TeamData.get(a).getAway_win()), Float.parseFloat(betIN_sporty.get(b).getAway_win()));


                    float arbitarageValue = ((1 / max_Homewin) * 100) + ((1 / max_draw) * 100) + ((1 / max_AwayWin) * 100);


                    if (arbitarageValue <= 99) {

                        positiveReturns = true;

                        //replace with your emailadress and password
//                        new Gmail("you'reEmail@gmail.com","password").sendMessage(
//                                "Site:" + betPawa.TeamData.get(a).getSite() + "\n" + "Home Team:-" + betPawa.TeamData.get(a).getHome_team() + ": " + betPawa.TeamData.get(a).getHome_win()
//                                        + " " + "Draw: " + betPawa.TeamData.get(a).getDraw() + " "
//                                        + "Away Team: " + betPawa.TeamData.get(a).getAway_team() + ": " + betPawa.TeamData.get(a).getAway_win() + " " + "\n" +
//                                        "Site:" + betIN_sporty.get(b).getSite() + "\n" + "Home Team: " + betIN_sporty.get(b).getHome_team() + ": " + betIN_sporty.get(b).getHome_win()
//                                        + " " + "Draw: " + betIN_sporty.get(b).getDraw() + " "
//                                        + "Away Team: " + betIN_sporty.get(b).getAway_team() + ": " + betIN_sporty.get(b).getAway_win()+"\n ARB_Value = "+arbitarageValue+"\n\n"
//
//                        );

                    }
                    System.out.println("ArbitarageValue: round 2 " + arbitarageValue);
                    System.out.println(positiveReturns);


                }
            }
            System.out.println();

        }


    }


    private static ArrayList<Bet> getMatch(ArrayList<Bet> ListA, ArrayList<Bet> ListB) {

        ArrayList<Bet> MatchesList = new ArrayList<>();


        for (int i = 0; i < ListA.size(); i++) {
            Bet Matches = new Bet();
            for (int x = 0; x < ListB.size(); x++) {

                if (similarity(ListA.get(i).getHome_team(), ListB.get(x).getHome_team()) >= 0.7 && similarity(ListA.get(i).getAway_team(), ListB.get(x).getAway_team()) >= 0.7) {
                    System.out.println("Site:" + ListA.get(i).getSite() + " \n" + "Home Team: " + ListA.get(i).getHome_team() + ": " + ListA.get(i).getHome_win()
                            + " " + "Draw: " + ListA.get(i).getDraw() + " "
                            + "Away Team: " + ListA.get(i).getAway_team() + ": " + ListA.get(i).getAway_win() + " " + "\n" +
                            "Site:" + ListB.get(x).getSite() + "\n" + "Home Team: " + ListB.get(x).getHome_team() + ": " + ListB.get(x).getHome_win()
                            + " " + "Draw: " + ListB.get(x).getDraw() + " "
                            + "Away Team: " + ListB.get(x).getAway_team() + ": " + ListB.get(x).getAway_win());


                    float max_Homewin = Math.max(Float.parseFloat(ListA.get(i).getHome_win()), Float.parseFloat(ListB.get(x).getHome_win()));
                    float max_draw = Math.max(Float.parseFloat(ListA.get(i).getDraw()), Float.parseFloat(ListB.get(x).getDraw()));
                    float max_AwayWin = Math.max(Float.parseFloat(ListA.get(i).getAway_win()), Float.parseFloat(ListB.get(x).getAway_win()));


                    float arbitarageValue = ((1 / max_Homewin) * 100) + ((1 / max_draw) * 100) + ((1 / max_AwayWin) * 100);
                    System.out.println("ArbitarageValue: after 2 sites " + arbitarageValue);



                    Matches.setSite("BetIN - SportyBet");
                    Matches.setHome_team(ListA.get(i).getHome_team());
                    Matches.setAway_team(ListA.get(i).getAway_team());
                    Matches.setHome_win(String.valueOf(max_Homewin));
                    Matches.setDraw(String.valueOf(max_draw));
                    Matches.setAway_win(String.valueOf(max_AwayWin));


                    MatchesList.add(Matches);
                }



            }
            System.out.println();

        }



        return MatchesList;
    }
    private static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }


}







