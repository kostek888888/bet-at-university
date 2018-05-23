package bet_at_university.database.model;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne()
    @JoinColumn(name = "bet_rate_id", nullable = false)
    private BetRate betRate;

    private String buyDateAndTime;

    private double moneyInserted;

    private String betType;

    private double amountToPaidOut;

    private int betResult;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Match getMatches() {
        return match;
    }

    public void setMatches(Match matches) {
        this.match = matches;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BetRate getBetRate() {
        return betRate;
    }

    public void setBetRate(BetRate betRate) {
        this.betRate = betRate;
    }

    public String getBuyDateAndTime() {
        return buyDateAndTime;
    }

    public void setBuyDateAndTime(String buyDateAndTime) {
        this.buyDateAndTime = buyDateAndTime;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(double moneyInserted) {
        this.moneyInserted = moneyInserted;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public double getAmountToPaidOut() {
        return amountToPaidOut;
    }

    public void setAmountToPaidOut(double amountToPaidOut) {
        this.amountToPaidOut = amountToPaidOut;
    }

    public int getBetResult() {
        return betResult;
    }

    public void setBetResult(int betResult) {
        this.betResult = betResult;
    }
}
