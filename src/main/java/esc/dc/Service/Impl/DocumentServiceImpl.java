package esc.dc.Service.Impl;

import esc.dc.Model.Document;
import esc.dc.Repository.DocumentRepository;
import esc.dc.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    private DocumentRepository DocumentRepository;


    @Override
    public List<Document> findAll() {
        return DocumentRepository.findAll();
    }

    @Override
    public Optional<Document> findById(int id) {
        return DocumentRepository.findById(id);
    }


    @Override
    public Document save(Document Document) {
        return DocumentRepository.save(Document);
    }
}
