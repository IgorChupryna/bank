package ua.chup.account;
import ua.chup.dao.BaseDao;

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

    @Column(name = "user_id")
    private int userId;

    @Column
    private double summ;

    public Account() {
    }

    public Account(String number, String type, int userId, double summ) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }
}
