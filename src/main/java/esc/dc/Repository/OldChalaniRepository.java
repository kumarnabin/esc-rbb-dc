package esc.dc.Repository;

import esc.dc.Model.OldChalani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldChalaniRepository extends JpaRepository<OldChalani,Integer> {

    List<OldChalani> findAllByRegionId(int regionId);

    List<OldChalani> findAllByRegionIdAndChalaniDateBetween(int regionId,String dartaStartDate, String dartaEndDate);

}
