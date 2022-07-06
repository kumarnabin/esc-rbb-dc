package esc.dc.Repository;

import esc.dc.Model.CcOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CcOrganizationRepository extends JpaRepository<CcOrganization,Integer> {
    Optional<CcOrganization> findById(int id);

    List<CcOrganization> findAll();
}
