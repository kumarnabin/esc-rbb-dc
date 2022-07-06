package esc.dc.Repository;

import esc.dc.Model.Chalani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChalaniRepository extends JpaRepository<Chalani, Integer> {


    int countByRegionId(int regionId);

    //true&false or person&office
    int countByRegionIdAndRecivType(int regionId, boolean recivType);

    Chalani findByIdAndRegionId(int id, int regionId);

    List<Chalani> findAllByRegionId(int regionId);

    List<Chalani> findAllByRegionIdAndRecivType(int regionId, boolean recivType);

    List<Chalani> findAllByRegionIdAndChalaniDateBetween(int regionId, String chalaniStartDate, String chalaniEndDate);


//    @Query("SELECT C.office,count(C.id) FROM Chalani C WHERE C.office is not null GROUP BY C.office")
//    ArrayList<Map<Office,Integer>> countTotalByOfficeData() throws Exception;

    @Query("SELECT C.office,count(C.id) FROM Chalani C WHERE C.office is not null GROUP BY C.office")
    List<Object[]> countTotalByOfficeData() throws Exception;

    List<Chalani> findAllByRegionIdAndChalaniDateStartsWith(int regionId, String chalaniDate);
}

