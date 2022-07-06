package esc.dc.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    Set<Position> positions;

}
