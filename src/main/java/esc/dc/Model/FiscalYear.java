package esc.dc.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
//@JsonIgnoreProperties("fiscal_yearsFields")
public class FiscalYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( unique = true)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end_at;

}
