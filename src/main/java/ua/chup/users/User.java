package ua.chup.users;
import ua.chup.dao.BaseDao;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @Column
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
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
