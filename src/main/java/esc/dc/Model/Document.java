package esc.dc.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subject;
    private String chalani_no;
    private String darta_no;
    private String body;
    private String remarks;

    @ManyToOne
    @MapsId("senderPersonId")
    @JoinColumn(name = "sender_person_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("senderPersonId")
    private Person senderPerson;

    @ManyToOne
    @MapsId("senderOrganizationId")
    @JoinColumn(name = "sender_organization_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("senderOrganizationId")
    private Organization senderOrganization;

    @ManyToOne
    @MapsId("receiverPersonId")
    @JoinColumn(name = "receiver_person_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("receiverPersonId")
    private Person receiverPerson;

    @ManyToOne
    @MapsId("receiverOrganizationId")
    @JoinColumn(name = "receiver_organization_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("receiverOrganizationId")
    private Organization receiverOrganization;

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

    @ManyToOne
    @MapsId("securityCategoryId")
    @JoinColumn(name = "security_category_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("securityCategoryId")
    private Dropdown security_category_id;

    @OneToMany(mappedBy = "document")
    private List<DocumentData> documentData;

    @OneToMany(mappedBy = "document")
    private List<CcOrganization> organizations;

    @OneToMany(mappedBy = "document")
    private List<CcPerson> persons;

    private String file;

    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;

    private Date viewed_at;

    private boolean is_external;
    private boolean is_completed;


}
