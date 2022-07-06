package esc.dc.Service;

import esc.dc.Model.Office;

import java.util.List;

public interface OfficeService {

    List<Office> findAllByRegionId(int regionId);

    Office findOne(int id);

    Office findOneAndRegion(int id, int regionId);

    Office save(Office office);
}
