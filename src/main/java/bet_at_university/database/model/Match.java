package bet_at_university.database.model;

import javax.persistence.*;

@Entity
@Table(name="\"match\"")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne()
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeamId;

    @OneToOne()
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeamId;

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
