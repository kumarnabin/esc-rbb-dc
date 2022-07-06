package esc.dc.Service.Impl;

import esc.dc.Model.CcPerson;
import esc.dc.Repository.CcPersonRepository;
import esc.dc.Service.CcPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CcPersonServiceImpl implements CcPersonService {


    @Autowired
    private CcPersonRepository CcPersonRepository;


    @Override
    public List<CcPerson> findAll() {
        return CcPersonRepository.findAll();
    }

    @Override
    public Optional<CcPerson> findById(int id) {
        return CcPersonRepository.findById(id);
    }


    @Override
    public CcPerson save(CcPerson CcPerson) {
        return CcPersonRepository.save(CcPerson);
    }
}
