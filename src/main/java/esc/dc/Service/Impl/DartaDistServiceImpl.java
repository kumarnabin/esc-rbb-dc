package esc.dc.Service.Impl;

import esc.dc.Model.DartaDist;
import esc.dc.Repository.DartaDistRepository;
import esc.dc.Service.DartaDistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DartaDistServiceImpl implements DartaDistService{

    @Autowired
    private DartaDistRepository dartaDistRepository;

    @Override
    public DartaDist findByIdAndRegionId(int id, int regionId) {
        return dartaDistRepository.findByIdAndRegionId(id,regionId);
    }

    @Override
    public DartaDist saveData(DartaDist dartaDist) {
        return dartaDistRepository.save(dartaDist);
    }

    @Override
    public List<DartaDist> findAllByRegionId(int regionId) {
        return dartaDistRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<DartaDist> findAllByRegionIdAndStatus(int regionId, boolean status) {
        return dartaDistRepository.findAllByRegionIdAndStatus(regionId,status);
    }
}
