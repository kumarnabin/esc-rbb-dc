package esc.dc.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "darta_dist")
public class DartaDist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public boolean status;

    @ManyToOne
    @JoinColumn(name = "darta_id",nullable = false,referencedColumnName = "id")
    private Darta darta;

    @ManyToOne
    @JoinColumn(name = "department_id",referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private Employee employee;

    @NotNull
    private int regionId;

    public DartaDist() {
    }

    public DartaDist(boolean status, Darta darta, Department department, Employee employee,int regionId) {
        this.status = status;
        this.darta = darta;
        this.department = department;
        this.employee = employee;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Darta getDarta() {
        return darta;
    }

    public void setDarta(Darta darta) {
        this.darta = darta;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getFormattedStatus() {
        return this.status?"Done":"Remaining";
    }

    @Override
    public String toString() {
        return "DartaDist{" +
                "id=" + id +
                ", status=" + status +
                ", darta=" + darta +
                ", department=" + department +
                ", employee=" + employee +
                ", regionId=" + regionId +
                '}';
    }
}
