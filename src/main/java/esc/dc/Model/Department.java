package esc.dc.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String name;

    @NotNull
    private int regionId;

    public int getCount() {
        return this.dartaDists.size();
    }

    public void setCount(int count) {
    }

    @OneToMany(mappedBy = "department")
    private List<DartaDist> dartaDists = new ArrayList<>();

    public List<DartaDist> getDartaDists() {
        return dartaDists;
    }

    public void setDartaDists(List<DartaDist> dartaDists) {
        this.dartaDists = dartaDists;
    }

    public Department() {
    }

    public Department(String name, @NotNull int regionId) {
        this.name = name;
        this.regionId = regionId;
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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
