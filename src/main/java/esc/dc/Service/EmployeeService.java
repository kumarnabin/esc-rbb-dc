package esc.dc.Service;

import esc.dc.Model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveData(Employee employee);

    Employee findByIdAndRegionId(int id,int regionId);

    List<Employee> findAllByRegionId(int regionId);

    List<Employee> findAllByRegionIdAndStatus(int regionId, boolean status);
}
