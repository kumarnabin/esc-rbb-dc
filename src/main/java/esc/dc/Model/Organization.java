package esc.dc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Organization organization;

    @ManyToOne()
    @JoinColumn(name = "place_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Place place;

    private String name;

    private int ward;

    @Column(length = 100)
    private String phone;

    @Column(length = 100)
    private String email;

    private String detail;

    //for values central, branch or department
    private String type;

    @OneToMany(mappedBy = "organization")
    private List<Organization> organizations;

    @OneToMany(mappedBy = "senderOrganization")
    private List<Document> sentDocuments;

    @OneToMany(mappedBy = "receiverOrganization")
    private List<Document> receivedDocuments;

}
