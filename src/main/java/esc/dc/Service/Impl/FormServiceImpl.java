package esc.dc.Service.Impl;

import esc.dc.Model.Form;
import esc.dc.Repository.FormRepository;
import esc.dc.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FormServiceImpl implements FormService {


    @Autowired
    private FormRepository formRepository;


    @Override
    public List<Form> findAll() {
        return formRepository.findAll();
    }

    @Override
    public Optional<Form> findById(int id) {
        return formRepository.findById(id);
    }


    @Override
    public Form save(Form form) {
        return formRepository.save(form);
    }
}
