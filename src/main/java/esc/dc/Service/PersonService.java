package esc.dc.Service;

import esc.dc.Model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(int id);

    Person save(Person person);
}
