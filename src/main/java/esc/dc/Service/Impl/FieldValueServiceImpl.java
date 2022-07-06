package esc.dc.Service.Impl;

import esc.dc.Model.DocumentData;
import esc.dc.Repository.FieldValueRepository;
import esc.dc.Service.FieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FieldValueServiceImpl implements FieldValueService {


    @Autowired
    private FieldValueRepository fieldValueRepository;


    @Override
    public List<DocumentData> findAll() {
        return fieldValueRepository.findAll();
    }

    @Override
    public Optional<DocumentData> findById(int id) {
        return fieldValueRepository.findById(id);
    }


    @Override
    public DocumentData save(DocumentData fieldValue) {
        return fieldValueRepository.save(fieldValue);
    }
}
