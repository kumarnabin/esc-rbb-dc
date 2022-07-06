package esc.dc.Repository;

import esc.dc.Model.NumberingFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberingFactorRepository extends JpaRepository<NumberingFactor,Integer> {


    NumberingFactor findByName(String name);
}
