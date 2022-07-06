package esc.dc.Service;

import esc.dc.Model.FiscalYear;

import java.util.List;
import java.util.Optional;

public interface FiscalYearService {

    List<FiscalYear> findAll();

    Optional<FiscalYear> findById(int id);

    FiscalYear save(FiscalYear fiscalYear);
}
