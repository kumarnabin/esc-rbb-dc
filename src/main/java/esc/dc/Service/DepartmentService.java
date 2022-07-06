package esc.dc.Service;

import esc.dc.Model.Department;

import java.util.List;

public interface DepartmentService {

    Department findOneByIdAndRegionId(int id, int regionId);

    Department saveData(Department department);

    List<Department> findAllByRegionId(int regionId);

    List<Department> findAll();
}
