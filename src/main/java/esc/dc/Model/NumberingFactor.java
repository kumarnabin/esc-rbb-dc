package esc.dc.Model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class NumberingFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @UniqueElements
    public String name;

    public NumberingFactor() {
    }

    public NumberingFactor(@UniqueElements String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "NumberingFactor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
