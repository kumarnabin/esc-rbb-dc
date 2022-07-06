package esc.dc.Repository;

import esc.dc.Model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form,Integer> {
    Optional<Form> findById(int id);

    List<Form> findAll();
}
