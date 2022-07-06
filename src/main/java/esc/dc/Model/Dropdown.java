package esc.dc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Dropdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @MapsId("dropdownId")
    @JoinColumn(name = "dropdown_id")
    private Dropdown dropdown;

    private String name;

    @OneToMany(mappedBy = "dropdown")
    private List<Dropdown> dropdowns;

    @OneToMany(mappedBy = "security_category_id")
    private List<Document> document;
}
