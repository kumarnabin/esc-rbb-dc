package esc.dc.Repository;

import esc.dc.Model.Region;
import esc.dc.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    List<User> findAllByRegion(Region region);

}
