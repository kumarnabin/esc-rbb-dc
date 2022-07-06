package esc.dc.Service;

import esc.dc.Model.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    List<Region> findAll();

    Optional<Region> findById(int id);

    Region save(Region region);
}
