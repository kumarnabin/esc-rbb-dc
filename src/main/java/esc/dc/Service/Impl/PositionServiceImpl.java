package esc.dc.Service.Impl;

import esc.dc.Model.Position;
import esc.dc.Repository.PositionRepository;
import esc.dc.Service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImpl implements PositionService {


    @Autowired
    private PositionRepository PositionRepository;


    @Override
    public List<Position> findAll() {
        return PositionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(int id) {
        return PositionRepository.findById(id);
    }


    @Override
    public Position save(Position Position) {
        return PositionRepository.save(Position);
    }
}
