package esc.dc.Repository;

import esc.dc.Model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office,Integer> {

    Office findByIdAndRegionId(int id, int regionId);

    List<Office> findAllByRegionId(int regionId);
}
