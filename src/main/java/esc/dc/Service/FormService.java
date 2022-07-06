package esc.dc.Service;

import esc.dc.Model.Form;

import java.util.List;
import java.util.Optional;

public interface FormService {

    List<Form> findAll();

    Optional<Form> findById(int id);

    Form save(Form form);
}
