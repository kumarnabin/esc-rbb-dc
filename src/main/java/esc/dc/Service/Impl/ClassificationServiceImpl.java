package esc.dc.Service.Impl;

import esc.dc.Model.Classification;
import esc.dc.Repository.ClassificationRepository;
import esc.dc.Service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClassificationServiceImpl implements ClassificationService {


    @Autowired
    private ClassificationRepository classificationRepository;


    @Override
    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    @Override
    public Optional<Classification> findById(int id) {
        return classificationRepository.findById(id);
    }


    @Override
    public Classification save(Classification classification) {
        return classificationRepository.save(classification);
    }
}
