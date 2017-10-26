package ua.chup.tranz;
import ua.chup.dao.BaseDao;

import javax.persistence.*;

@Entity
@Table(name = "Tranzaction")
public class Tranzaction extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @Column(name = "type_trn_id")
    private int typeTrnId;

    @Column(name = "type_cur_trn")
    private String typeCurTrn;

    @Column(name = "acc_debit")
    private String accDebit;

    @Column(name = "acc_credit")
    private String accCredit;

    @Column(name = "type_cur_acc_cr")
    private String typeCurAccCr;

    @Column
    private double value;

    @Column(name = "resp_id")
    private int respId;

    public Tranzaction() {
    }

    public Tranzaction(int typeTrnId, int respId) {
        this.typeTrnId = typeTrnId;
        this.respId = respId;
    }

    @Override
    public String toString() {
        return "Tranzaction{" +
                "id=" + id +
                ", typeTrnId=" + typeTrnId +
                ", typeCurTrn='" + typeCurTrn + '\'' +
                ", accDebit='" + accDebit + '\'' +
                ", accCredit='" + accCredit + '\'' +
                ", typeCurAccCr='" + typeCurAccCr + '\'' +
                ", value=" + value +
                ", respId=" + respId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeTrnId() {
        return typeTrnId;
    }

    public void setTypeTrnId(int typeTrnId) {
        this.typeTrnId = typeTrnId;
    }

    public String getTypeCurTrn() {
        return typeCurTrn;
    }

    public void setTypeCurTrn(String typeCurTrn) {
        this.typeCurTrn = typeCurTrn;
    }

    public String getAccDebit() {
        return accDebit;
    }

    public void setAccDebit(String accDebit) {
        this.accDebit = accDebit;
    }

    public String getAccCredit() {
        return accCredit;
    }

    public void setAccCredit(String accCredit) {
        this.accCredit = accCredit;
    }

    public String getTypeCurAccCr() {
        return typeCurAccCr;
    }

    public void setTypeCurAccCr(String typeCurAccCr) {
        this.typeCurAccCr = typeCurAccCr;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getRespId() {
        return respId;
    }

    public void setRespId(int respId) {
        this.respId = respId;
    }
}
