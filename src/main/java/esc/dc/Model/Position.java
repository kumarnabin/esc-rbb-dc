package esc.dc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    Set<Permission> permissions;

}
