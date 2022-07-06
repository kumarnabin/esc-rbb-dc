package esc.dc.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("positionId")
    private Position position;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("organizationId")
    private Organization organization;

    private String contact_no;
    private String email;
    private String detail;

    @OneToOne(mappedBy = "person")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @OneToMany(mappedBy = "senderPerson")
    private List<Document> sentDocuments;

    @OneToMany(mappedBy = "receiverPerson")
    private List<Document> receivedDocuments;

}
