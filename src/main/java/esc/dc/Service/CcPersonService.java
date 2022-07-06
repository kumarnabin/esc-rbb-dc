package esc.dc.Service;

import esc.dc.Model.CcPerson;

import java.util.List;
import java.util.Optional;

public interface CcPersonService {

    List<CcPerson> findAll();

    Optional<CcPerson> findById(int id);

    CcPerson save(CcPerson ccPerson);
}
