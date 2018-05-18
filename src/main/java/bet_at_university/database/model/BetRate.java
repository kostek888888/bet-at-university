package bet_at_university.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BetRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double homeTeamWinRate;

    private double awayTeamWinRate;

    private double drawRate;

    private double homeTeamWinOrDrawRate;

    private double awayTeamWinOrDrawRate;

    private double homeWinOrAwayWin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getHomeTeamWinRate() {
        return homeTeamWinRate;
    }

    public void setHomeTeamWinRate(double homeTeamWinRate) {
        this.homeTeamWinRate = homeTeamWinRate;
    }

    public double getAwayTeamWinRate() {
        return awayTeamWinRate;
    }

    public void setAwayTeamWinRate(double awayTeamWinRate) {
        this.awayTeamWinRate = awayTeamWinRate;
    }

    public double getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(double drawRate) {
        this.drawRate = drawRate;
    }

    public double getHomeTeamWinOrDrawRate() {
        return homeTeamWinOrDrawRate;
    }

    public void setHomeTeamWinOrDrawRate(double homeTeamWinOrDrawRate) {
        this.homeTeamWinOrDrawRate = homeTeamWinOrDrawRate;
    }

    public double getAwayTeamWinOrDrawRate() {
        return awayTeamWinOrDrawRate;
    }

    public void setAwayTeamWinOrDrawRate(double awayTeamWinOrDrawRate) {
        this.awayTeamWinOrDrawRate = awayTeamWinOrDrawRate;
    }

    public double getHomeWinOrAwayWin() {
        return homeWinOrAwayWin;
    }

    public void setHomeWinOrAwayWin(double homeWinOrAwayWin) {
        this.homeWinOrAwayWin = homeWinOrAwayWin;
    }
}
