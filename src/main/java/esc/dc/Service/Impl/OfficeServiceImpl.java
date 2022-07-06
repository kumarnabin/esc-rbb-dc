package esc.dc.Service.Impl;

import esc.dc.Model.Office;
import esc.dc.Repository.OfficeRepository;
import esc.dc.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {


    @Autowired
    private OfficeRepository officeRepository;


    @Override
    public List<Office> findAllByRegionId(int regionId) {
        return officeRepository.findAllByRegionId(regionId);
    }

    @Override
    public Office findOne(int id) {
        return officeRepository.findById(id).get();
    }

    @Override
    public Office findOneAndRegion(int id, int regionId) {
        return officeRepository.findByIdAndRegionId(id, regionId);
    }

    @Override
    public Office save(Office office) {
        return officeRepository.save(office);
    }
}
