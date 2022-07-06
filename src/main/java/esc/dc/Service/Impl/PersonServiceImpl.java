package esc.dc.Service.Impl;

import esc.dc.Model.Person;
import esc.dc.Repository.PersonRepository;
import esc.dc.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository PersonRepository;


    @Override
    public List<Person> findAll() {
        return PersonRepository.findAll();
    }

    @Override
    public Optional<Person> findById(int id) {
        return PersonRepository.findById(id);
    }


    @Override
    public Person save(Person Person) {
        return PersonRepository.save(Person);
    }
}
