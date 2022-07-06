package esc.dc.Repository;

import esc.dc.Model.Darta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DartaRepository extends JpaRepository<Darta,Integer>{


    int countByRegionId(int regionId);

    //true&false or person&office
    int countByRegionIdAndRecivType(int regionId, boolean recivType);

    Darta findByIdAndRegionId(int id , int regionId);

    List<Darta> findAllByRegionId(int regionId);

    List<Darta> findAllByRegionIdAndRecivType(int regionId, boolean recivType);

    List<Darta> findAllByRegionIdAndDartaDateBetween(int regionId, String dartaStartDate, String dartaEndDate);

    List<Darta> findAllByRegionIdAndDartaDateStartsWith(int region_id, String month_date);
}
