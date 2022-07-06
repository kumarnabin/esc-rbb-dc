package esc.dc.Service.Impl;

import esc.dc.Model.Region;
import esc.dc.Repository.RegionRepository;
import esc.dc.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RegionServiceImpl implements RegionService {


    @Autowired
    private RegionRepository RegionRepository;


    @Override
    public List<Region> findAll() {
        return RegionRepository.findAll();
    }

    @Override
    public Optional<Region> findById(int id) {
        return RegionRepository.findById(id);
    }


    @Override
    public Region save(Region region) {
        return RegionRepository.save(region);
    }
}
