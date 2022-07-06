package esc.dc.Service;

import esc.dc.Model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    List<Place> findAll();

    Optional<Place> findById(int id);

    Place save(Place place);
}
