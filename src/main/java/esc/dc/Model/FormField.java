package esc.dc.Model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@JsonIgnoreProperties("form")
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @MapsId("formId")
    @JoinColumn(name = "form_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("formId")
    private Form form;

    @Column( unique = true, nullable = false)
    private String name;

    private int queue;

    @OneToMany(mappedBy = "formField")
    private List<DocumentData> fieldValues;

}
