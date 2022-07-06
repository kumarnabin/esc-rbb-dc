package esc.dc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @MapsId("placeId")
    @JoinColumn(name = "place_id")
    private Place place;

    private String name;

    private int wards;

    @Column(length = 100)
    private String type;

    @OneToMany(mappedBy = "place")
    private List<Place> places;
}
