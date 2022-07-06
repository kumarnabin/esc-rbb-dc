package esc.dc.Service.Impl;

import esc.dc.Model.CcOrganization;
import esc.dc.Repository.CcOrganizationRepository;
import esc.dc.Service.CcOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CcOrganizationServiceImpl implements CcOrganizationService {


    @Autowired
    private CcOrganizationRepository CcOrganizationRepository;


    @Override
    public List<CcOrganization> findAll() {
        return CcOrganizationRepository.findAll();
    }

    @Override
    public Optional<CcOrganization> findById(int id) {
        return CcOrganizationRepository.findById(id);
    }


    @Override
    public CcOrganization save(CcOrganization CcOrganization) {
        return CcOrganizationRepository.save(CcOrganization);
    }
}
