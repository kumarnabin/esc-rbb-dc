package esc.dc.Service.Impl;

import esc.dc.Model.Dropdown;
import esc.dc.Repository.DropdownRepository;
import esc.dc.Service.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DropdownServiceImpl implements DropdownService {


    @Autowired
    private DropdownRepository DropdownRepository;


    @Override
    public List<Dropdown> findAll() {
        return DropdownRepository.findAll();
    }

    @Override
    public Optional<Dropdown> findById(int id) {
        return DropdownRepository.findById(id);
    }


    @Override
    public Dropdown save(Dropdown Dropdown) {
        return DropdownRepository.save(Dropdown);
    }
}
