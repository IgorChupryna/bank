package ua.chup.account;
import ua.chup.dao.BaseDao;
import ua.chup.users.User;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @Column
    private String number;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column
    private double summ;

    public Account() {
    }

    public Account(String number, String type, User userId, double summ) {
        this.number = number;
        this.type = type;
        this.userId = userId;
        this.summ = summ;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", summ=" + summ +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }
}
