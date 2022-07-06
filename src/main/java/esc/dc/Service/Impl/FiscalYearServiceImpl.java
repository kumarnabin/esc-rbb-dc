package esc.dc.Service.Impl;

import esc.dc.Model.FiscalYear;
import esc.dc.Repository.FiscalYearRepository;
import esc.dc.Service.FiscalYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FiscalYearServiceImpl implements FiscalYearService {


    @Autowired
    private FiscalYearRepository fiscalYearRepository;


    @Override
    public List<FiscalYear> findAll() {
        return fiscalYearRepository.findAll();
    }

    @Override
    public Optional<FiscalYear> findById(int id) {
        return fiscalYearRepository.findById(id);
    }


    @Override
    public FiscalYear save(FiscalYear fiscalYear) {
        return fiscalYearRepository.save(fiscalYear);
    }
}
