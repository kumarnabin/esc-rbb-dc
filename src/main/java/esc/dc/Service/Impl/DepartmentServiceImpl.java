package esc.dc.Service.Impl;

import esc.dc.Model.Department;
import esc.dc.Repository.DepartmentRepository;
import esc.dc.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findOneByIdAndRegionId(int id, int regionId) {
        return departmentRepository.findByIdAndRegionId(id,regionId);
    }

    @Override
    public Department saveData(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAllByRegionId(int regionId) {
        return departmentRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

}
