package esc.dc.Service;

import esc.dc.Model.DocumentData;

import java.util.List;
import java.util.Optional;

public interface FieldValueService {

    List<DocumentData> findAll();

    Optional<DocumentData> findById(int id);

    DocumentData save(DocumentData fieldValue);
}
