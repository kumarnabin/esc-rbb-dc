package esc.dc.Repository;

import esc.dc.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    List<Employee> findAllByRegionIdAndStatus(int regionId,boolean status);

    List<Employee> findAllByRegionId(int regionId);

    Employee findByIdAndRegionId(int id, int regionId);
}
