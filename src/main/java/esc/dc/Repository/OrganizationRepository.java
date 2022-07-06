package esc.dc.Repository;

import esc.dc.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Optional<Organization> findById(int id);

    List<Organization> findAll();
}
