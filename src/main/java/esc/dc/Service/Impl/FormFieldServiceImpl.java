package esc.dc.Service.Impl;

import esc.dc.Model.FormField;
import esc.dc.Repository.FormFieldRepository;
import esc.dc.Service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FormFieldServiceImpl implements FormFieldService {


    @Autowired
    private FormFieldRepository formFieldRepository;


    @Override
    public List<FormField> findAll() {
        return formFieldRepository.findAll();
    }

    @Override
    public Optional<FormField> findById(int id) {
        return formFieldRepository.findById(id);
    }


    @Override
    public FormField save(FormField FormField) {
        return formFieldRepository.save(FormField);
    }
}
