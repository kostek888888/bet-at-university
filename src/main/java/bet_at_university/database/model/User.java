package bet_at_university.database.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne()
    @JoinColumn(name = "user_statistics_id")
    private UserStatistics userStatistics;

    private String name;

    private String surname;

    private String birthDate;

    private String address;

    private String email;

    private String postCode;

    @Column(name = "login", unique = true)
    private String login;

    private String password;

    private String facebookId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
