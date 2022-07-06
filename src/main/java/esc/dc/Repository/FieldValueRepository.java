package esc.dc.Repository;

import esc.dc.Model.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldValueRepository extends JpaRepository<DocumentData,Integer> {
    Optional<DocumentData> findById(int id);

    List<DocumentData> findAll();
}
