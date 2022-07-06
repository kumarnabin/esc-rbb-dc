package esc.dc.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
//@JsonIgnoreProperties("formFields")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String subject;

    private String content;

    private String file;

    @OneToMany(mappedBy = "form")
    private List<FormField> formFields;

}
