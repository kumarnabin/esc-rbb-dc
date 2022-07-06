package esc.dc.Service;

import esc.dc.Model.CcOrganization;

import java.util.List;
import java.util.Optional;

public interface CcOrganizationService {

    List<CcOrganization> findAll();

    Optional<CcOrganization> findById(int id);

    CcOrganization save(CcOrganization ccOrganization);
}
