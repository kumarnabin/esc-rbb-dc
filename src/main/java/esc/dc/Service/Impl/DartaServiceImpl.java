package esc.dc.Service.Impl;

import esc.dc.Model.Darta;
import esc.dc.Repository.DartaRepository;
import esc.dc.Service.DartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DartaServiceImpl implements DartaService {

    @Autowired
    private DartaRepository dartaRepository;

    @Override
    public Darta findOne(int id) {
        return dartaRepository.findById(id).get();
    }

    @Override
    public Darta findOneAndRegion(int id, int regionId) {
        return dartaRepository.findByIdAndRegionId(id, regionId);
    }

    @Override
    public Darta saveDarta(Darta darta) {
        return dartaRepository.save(darta);
    }

    @Override
    public List<Darta> findAllByRegionId(int regionId) {
        return dartaRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Darta> findAllByRegionIdAndDartaDateBetween(int regionId, String dartaStartDate, String dartaEndDate) {
        return dartaRepository.findAllByRegionIdAndDartaDateBetween(regionId, dartaStartDate, dartaEndDate);
    }


    @Override
    public List<Darta> findAllByRegionIdAndRevicType(int regionId, boolean recivType) {
        return dartaRepository.findAllByRegionIdAndRecivType(regionId, recivType);
    }

    @Override
    public int countAllByRegionId(int regionId) {
        return dartaRepository.countByRegionId(regionId);
    }

    @Override
    public int[] countAllByRegionIdAndRecivType(int regionId, boolean recivType) {
        int total = dartaRepository.countByRegionId(regionId);
        int countByPerson = dartaRepository.countByRegionIdAndRecivType(regionId, true);
        int countByOffice = total - countByPerson;
        return new int[]{countByPerson, countByOffice};
    }

    @Override
    public List<Darta> findAllByRegionIdAndDartaDateStartsWith(int region_id, String month_date) {
        return dartaRepository.findAllByRegionIdAndDartaDateStartsWith(region_id,  month_date);
    }
}
