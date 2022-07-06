package esc.dc.Service;

import esc.dc.Model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    List<Position> findAll();

    Optional<Position> findById(int id);

    Position save(Position position);
}
