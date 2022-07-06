package esc.dc.Service;

import esc.dc.Model.FormField;

import java.util.List;
import java.util.Optional;

public interface FormFieldService {

    List<FormField> findAll();

    Optional<FormField> findById(int id);

    FormField save(FormField formField);
}
