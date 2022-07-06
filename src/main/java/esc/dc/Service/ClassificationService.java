package esc.dc.Service;

import esc.dc.Model.Classification;

import java.util.List;
import java.util.Optional;

public interface ClassificationService {

    List<Classification> findAll();

    Optional<Classification> findById(int id);

    Classification save(Classification classification);
}
