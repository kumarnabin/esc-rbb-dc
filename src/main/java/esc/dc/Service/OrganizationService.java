package esc.dc.Service;

import esc.dc.Model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    List<Organization> findAll();

    Optional<Organization> findById(int id);

    Organization save(Organization organization);
}
