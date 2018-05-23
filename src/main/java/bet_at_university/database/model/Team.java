package bet_at_university.database.model;

import javax.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne()
    @JoinColumn(name = "team_statistics_id", nullable = false)
    private TeamStatistics teamStatistics;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamStatistics getTeamStatistics() {
        return teamStatistics;
    }

    public void setTeamStatistics(TeamStatistics teamStatistics) {
        this.teamStatistics = teamStatistics;
    }
}
