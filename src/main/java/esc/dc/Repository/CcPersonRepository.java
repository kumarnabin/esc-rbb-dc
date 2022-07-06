package esc.dc.Repository;

import esc.dc.Model.CcPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CcPersonRepository extends JpaRepository<CcPerson,Integer> {
    Optional<CcPerson> findById(int id);

    List<CcPerson> findAll();
}
