package esc.dc.Repository;

import esc.dc.Model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    Optional<Place> findById(int id);

    List<Place> findAll();
}
