package esc.dc.Repository;

import esc.dc.Model.DartaDist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DartaDistRepository extends JpaRepository<DartaDist,Integer>{

    DartaDist findByIdAndRegionId(int id,int regionId);

    List<DartaDist> findAllByRegionId(int regionId);

    List<DartaDist> findAllByRegionIdAndStatus(int regionId, boolean status);
}
