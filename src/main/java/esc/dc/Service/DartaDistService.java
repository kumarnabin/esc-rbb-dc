package esc.dc.Service;

import esc.dc.Model.DartaDist;

import java.util.List;

public interface DartaDistService {

    DartaDist findByIdAndRegionId(int id, int regionId);

    DartaDist saveData(DartaDist dartaDist);

    List<DartaDist> findAllByRegionId(int regionId);

    List<DartaDist> findAllByRegionIdAndStatus(int regionId, boolean status);
}
