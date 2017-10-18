package ua.chup.currency;
import ua.chup.dao.BaseDao;

import javax.persistence.*;

@Entity
@Table(name = "Corrency")
public class Corrency extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;



    @Column(name = "type_in")
    private String typeIn;

    @Column(name = "type_out")
    private String typeOut;


    @Column
    private double value;

    public Corrency() {
    }

    public Corrency(String typeIn, String typeOut, double value) {
        this.typeIn = typeIn;
        this.typeOut = typeOut;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Corrency{" +
                "id=" + id +
                ", typeIn='" + typeIn + '\'' +
                ", typeOut='" + typeOut + '\'' +
                ", value=" + value +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeIn() {
        return typeIn;
    }

    public void setTypeIn(String typeIn) {
        this.typeIn = typeIn;
    }

    public String getTypeOut() {
        return typeOut;
    }

    public void setTypeOut(String typeOut) {
        this.typeOut = typeOut;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
