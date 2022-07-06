package esc.dc.Repository;

import esc.dc.Model.FiscalYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiscalYearRepository extends JpaRepository<FiscalYear, Integer> {
    Optional<FiscalYear> findById(int id);

    List<FiscalYear> findAll();
}
