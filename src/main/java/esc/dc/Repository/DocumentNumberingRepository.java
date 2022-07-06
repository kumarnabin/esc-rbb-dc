package esc.dc.Repository;

import esc.dc.Model.DocumentNumbering;
import esc.dc.Model.NumberingFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentNumberingRepository extends JpaRepository<DocumentNumbering,Integer> {

    List<DocumentNumbering> findByRegionId(int regionId);

    DocumentNumbering findByIdAndRegionId(int id, int regionId);

    DocumentNumbering findByRegionIdAndNumberingFactor(int regionId, NumberingFactor numberingFactor);
}
