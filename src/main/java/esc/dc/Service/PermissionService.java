package esc.dc.Service;

import esc.dc.Model.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    List<Permission> findAll();

    Optional<Permission> findById(int id);

    Permission save(Permission permission);
}
