package esc.dc.Service;

import esc.dc.Model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<Document> findAll();

    Optional<Document> findById(int id);

    Document save(Document document);
}
