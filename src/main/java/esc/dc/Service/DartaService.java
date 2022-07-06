package esc.dc.Service;

import esc.dc.Model.Darta;

import java.util.List;
import java.util.Map;

public interface DartaService {

    Darta findOne(int id);

    Darta findOneAndRegion(int id, int regionId);

    Darta saveDarta(Darta darta);

    List<Darta> findAllByRegionId(int regionId);

    List<Darta> findAllByRegionIdAndDartaDateBetween(int regionId,String dartaStartDate,String dartaEndDate);

    List<Darta> findAllByRegionIdAndRevicType(int regionId, boolean recivType);

    int countAllByRegionId(int regionId);

    //true&false or person&office
    int[] countAllByRegionIdAndRecivType(int regionId, boolean recivType);

    List<Darta> findAllByRegionIdAndDartaDateStartsWith(int region_id, String month_date);
}
