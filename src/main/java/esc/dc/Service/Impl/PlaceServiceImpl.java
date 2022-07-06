package esc.dc.Service.Impl;

import esc.dc.Model.Place;
import esc.dc.Repository.PlaceRepository;
import esc.dc.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlaceServiceImpl implements PlaceService {


    @Autowired
    private PlaceRepository placeRepository;


    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> findById(int id) {
        return placeRepository.findById(id);
    }


    @Override
    public Place save(Place place) {
        return placeRepository.save(place);
    }
}
