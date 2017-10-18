package ua.chup.type;
import ua.chup.dao.BaseDao;

import javax.persistence.*;

@Entity
@Table(name = "Type")
public class Type extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @Column
    private String name;

    public Type(String name) {
        this.name = name;
    }

    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
