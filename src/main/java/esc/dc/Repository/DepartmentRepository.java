package esc.dc.Repository;

import esc.dc.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{

    List<Department> findAllByRegionId(int regionId);

    Department findByIdAndRegionId(int id,int regionId);
}
