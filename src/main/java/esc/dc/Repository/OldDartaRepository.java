package esc.dc.Repository;

import esc.dc.Model.OldDarta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldDartaRepository extends JpaRepository<OldDarta,Integer> {
    List<OldDarta> findAllByRegionId(int regionId);

    List<OldDarta> findAllByRegionIdAndDartaDateBetween(int regionId,String dartaStartDate, String dartaEndDate);
}
