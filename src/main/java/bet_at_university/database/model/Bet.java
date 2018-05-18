package bet_at_university.database.model;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Matches matches;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
