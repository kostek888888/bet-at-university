package bet_at_university.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int wonMatches;

    private int lostMatches;

    private int biggestWin;

    private String biggestWinDate;

    private int accountBalance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWonMatches() {
        return wonMatches;
    }

    public void setWonMatches(int wonMatches) {
        this.wonMatches = wonMatches;
    }

    public int getLostMatches() {
        return lostMatches;
    }

    public void setLostMatches(int lostMatches) {
        this.lostMatches = lostMatches;
    }

    public int getBiggestWin() {
        return biggestWin;
    }

    public void setBiggestWin(int biggestWin) {
        this.biggestWin = biggestWin;
    }

    public String getBiggestWinDate() { return biggestWinDate; }

    public void setBiggestWinDate(String biggestWinDate) {
        this.biggestWinDate = biggestWinDate;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
}
