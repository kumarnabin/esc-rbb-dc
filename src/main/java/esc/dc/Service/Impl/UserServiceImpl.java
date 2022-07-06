package esc.dc.Service.Impl;

import esc.dc.Model.Region;
import esc.dc.Model.User;
import esc.dc.Repository.RegionRepository;
import esc.dc.Repository.UserRepository;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RegionRepository regionRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAllByRegion(Region region){
        return userRepository.findAllByRegion(region);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUserBySuper(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        return userRepository.save(user);
    }

    @Override
    public void saveUserByAdmin(User user, int regionId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRegion(regionRepository.findById(regionId).get());
        userRepository.save(user);
    }

    @Override
    public boolean checkOldPassword(User user,String oldPassword) {
        return bCryptPasswordEncoder.matches(oldPassword,user.getPassword());
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
