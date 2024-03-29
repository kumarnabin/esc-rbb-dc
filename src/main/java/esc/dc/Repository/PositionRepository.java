package esc.dc.Repository;

import esc.dc.Model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
    Optional<Position> findById(int id);

    List<Position> findAll();
}
