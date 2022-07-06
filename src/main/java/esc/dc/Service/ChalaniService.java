package esc.dc.Service;

import esc.dc.Model.Chalani;

import java.util.List;

public interface ChalaniService {

    Chalani findOne(int id);

    Chalani findOneAndRegion(int id, int regionId);

    Chalani saveChalani(Chalani chalani);

    List<Chalani> findAllByRegionId(int regionId);

    List<Chalani> findAllByRegionIdAndChalaniDateBetween(int regionId,String chalaniStartDate,String chalaniEndDate);

    List<Chalani> findAllByRegionIdAndRevicType(int regionId, boolean recivType);

    int countAllByRegionId(int regionId);

    //true&false or person&office
    int[] countAllByRegionIdAndRecivType(int regionId, boolean recivType);



    List<Chalani> findAllByRegionIdAndChalaniDateStartsWith(int regionId,String chalaniDate);

}
