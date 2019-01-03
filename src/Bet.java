public class Bet {
    private String Home_team,Away_team,home_win,draw,away_win,site;
    private String date;


    public Bet() {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getHome_team() {
        return Home_team;
    }

    public void setHome_team(String home_team) {
        Home_team = home_team;
    }

    public String getAway_team() {
        return Away_team;
    }

    public void setAway_team(String away_team) {
        Away_team = away_team;
    }

    public String getHome_win() {
        return home_win;
    }

    public void setHome_win(String home_win) {
        this.home_win = home_win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getAway_win() {
        return away_win;
    }

    public void setAway_win(String away_win) {
        this.away_win = away_win;
    }

    public void setSite(String site){
        this.site = site;
    }
    public String getSite(){

        return site;
    }
    public String toString(){

        return "Site:"+site+"/n Home Team: "+Home_team+": "+ home_win+"\n"+"Draw:"+ draw+"\n"
                +"Away Team:" +Away_team+": "+ away_win +" \n"+ "Date:"+getDate();
    }


}
