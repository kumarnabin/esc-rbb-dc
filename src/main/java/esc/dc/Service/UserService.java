package esc.dc.Service;

import esc.dc.Model.Region;
import esc.dc.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    User saveUser(User user,int regionId) throws Exception;

    List<User> findAllByRegion(Region region);

    User findUserByEmail(String email);

    Optional<User> findById(Integer id);

    User saveUserBySuper(User user);

    void saveUserByAdmin(User user, int regionId);

    boolean checkOldPassword(User user, String oldPassword);

    List<User> findAllUser();
}
