package esc.dc.Service.Impl;

import esc.dc.Model.Permission;
import esc.dc.Repository.PermissionRepository;
import esc.dc.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionRepository PermissionRepository;


    @Override
    public List<Permission> findAll() {
        return PermissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(int id) {
        return PermissionRepository.findById(id);
    }


    @Override
    public Permission save(Permission Permission) {
        return PermissionRepository.save(Permission);
    }
}
