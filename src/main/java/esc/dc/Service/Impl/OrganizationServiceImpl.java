package esc.dc.Service.Impl;

import esc.dc.Model.Organization;
import esc.dc.Repository.OrganizationRepository;
import esc.dc.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationRepository OrganizationRepository;


    @Override
    public List<Organization> findAll() {
        return OrganizationRepository.findAll();
    }

    @Override
    public Optional<Organization> findById(int id) {
        return OrganizationRepository.findById(id);
    }


    @Override
    public Organization save(Organization Organization) {
        return OrganizationRepository.save(Organization);
    }
}
