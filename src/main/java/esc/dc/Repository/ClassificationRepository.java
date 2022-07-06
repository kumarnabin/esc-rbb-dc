package esc.dc.Repository;

import esc.dc.Model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {

    Optional<Classification> findById(int id);

    List<Classification> findAll();
}
