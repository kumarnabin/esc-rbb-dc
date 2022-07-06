package esc.dc.Service.Impl;

import esc.dc.Model.Chalani;
import esc.dc.Repository.ChalaniRepository;
import esc.dc.Service.ChalaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChalaniServiceImpl implements ChalaniService {

    @Autowired
    private ChalaniRepository chalaniRepository;

    @Override
    public Chalani findOne(int id) {
        return chalaniRepository.findById(id).get();
    }

    @Override
    public Chalani findOneAndRegion(int id, int regionId) {
        return chalaniRepository.findByIdAndRegionId(id,regionId);
    }

    @Override
    public Chalani saveChalani(Chalani chalani) {
        return chalaniRepository.save(chalani);
    }

    @Override
    public List<Chalani> findAllByRegionId(int regionId) {
        return chalaniRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Chalani> findAllByRegionIdAndChalaniDateBetween(int regionId, String chalaniStartDate, String chalaniEndDate) {
        return chalaniRepository.findAllByRegionIdAndChalaniDateBetween(regionId,chalaniStartDate,chalaniEndDate);
    }

    @Override
    public List<Chalani> findAllByRegionIdAndRevicType(int regionId, boolean recivType) {
        return chalaniRepository.findAllByRegionIdAndRecivType(regionId,recivType);
    }

    @Override
    public int countAllByRegionId(int regionId) {
        return chalaniRepository.countByRegionId(regionId);
    }

    @Override
    public int[] countAllByRegionIdAndRecivType(int regionId, boolean recivType) {
        int total = chalaniRepository.countByRegionId(regionId);
        int countByPerson = chalaniRepository.countByRegionIdAndRecivType(regionId,true);
        int countByOffice = total-countByPerson;
        int[] arrChalani={countByPerson,countByOffice};
        return arrChalani;
    }

    @Override
    public List<Chalani> findAllByRegionIdAndChalaniDateStartsWith(int regionId, String chalaniDate) {
        return chalaniRepository.findAllByRegionIdAndChalaniDateStartsWith(regionId, chalaniDate);
    }

}
