package esc.dc.Service.Impl;

import esc.dc.Model.Employee;
import esc.dc.Repository.EmployeeRepository;
import esc.dc.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveData(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByIdAndRegionId(int id, int regionId) {
        return employeeRepository.findByIdAndRegionId(id,regionId);
    }

    @Override
    public List<Employee> findAllByRegionId(int regionId) {
        return employeeRepository.findAllByRegionId(regionId);
    }

    @Override
    public List<Employee> findAllByRegionIdAndStatus(int regionId, boolean status) {
        return employeeRepository.findAllByRegionIdAndStatus(regionId,status);
    }
}
