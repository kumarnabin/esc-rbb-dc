package esc.dc.Repository;

import esc.dc.Model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField,Integer> {
    Optional<FormField> findById(int id);

    List<FormField> findAll();
}
