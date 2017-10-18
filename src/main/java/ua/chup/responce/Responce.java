package ua.chup.responce;
import ua.chup.dao.BaseDao;

import javax.persistence.*;

@Entity
@Table(name = "Responce")
public class Responce extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @Column
    private String name;

    public Responce(String name) {
        this.name = name;
    }

    public Responce() {
    }

    @Override
    public String toString() {
        return "Responce{" +
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
