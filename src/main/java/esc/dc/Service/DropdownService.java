package esc.dc.Service;

import esc.dc.Model.Dropdown;

import java.util.List;
import java.util.Optional;

public interface DropdownService {

    List<Dropdown> findAll();

    Optional<Dropdown> findById(int id);

    Dropdown save(Dropdown dropdown);
}
