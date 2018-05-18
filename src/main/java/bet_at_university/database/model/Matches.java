package bet_at_university.database.model;

import javax.persistence.*;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeamId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeamId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bet_rate_id", nullable = false)
    private BetRate betRate;

    private int homeTeamScore;

    private int awayTeamScore;

    private String matchDateAndTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Team homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Team getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Team awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public BetRate getBetRate() {
        return betRate;
    }

    public void setBetRate(BetRate betRate) {
        this.betRate = betRate;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getMatchDateAndTime() {
        return matchDateAndTime;
    }

    public void setMatchDateAndTime(String matchDateAndTime) {
        this.matchDateAndTime = matchDateAndTime;
    }
}
