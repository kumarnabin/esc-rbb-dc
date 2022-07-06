package esc.dc.Repository;

import esc.dc.Model.Classification;
import esc.dc.Model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {
    Optional<Region> findById(int id);

    List<Region> findAll();
}
