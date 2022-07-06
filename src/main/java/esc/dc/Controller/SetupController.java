package esc.dc.Controller;

import esc.dc.Model.NumberingFactor;
import esc.dc.Model.Region;
import esc.dc.Model.Role;
import esc.dc.Repository.NumberingFactorRepository;
import esc.dc.Repository.RegionRepository;
import esc.dc.Repository.RoleRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/set")
public class SetupController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private NumberingFactorRepository numberingFactorRepository;

    @GetMapping(value = "/role")
    public String roleSetup() throws SQLException {
        List<Role> roles = roleRepository.findAll();
        System.out.println(roles);
        if (roles.isEmpty()) {
            roles.add(new Role("SUPERADMIN"));
            roles.add(new Role("CREATER"));
            roles.add(new Role("ADMIN"));
            roles.add(new Role("VIEWER"));
            for (Role role : roles) {

                roleRepository.save(role);
            }
            return "message/added";
        }
        return "message/init";
    }

    @GetMapping(value = "/numberingFactor")
    public String numberingFactorSetup() throws SQLException {
        List<NumberingFactor> nfs = numberingFactorRepository.findAll();
        System.out.println(nfs);
        if (nfs.isEmpty()) {
            nfs.add(new NumberingFactor("darta"));
            nfs.add(new NumberingFactor("chalani"));
            for (NumberingFactor nf : nfs) {

                numberingFactorRepository.save(nf);
            }

            return "message/added";
        }
        return "message/init";

    }

    @GetMapping(value = "/region")
    public String regionSetup() throws SQLException {
        List<Region> regions = regionRepository.findAll();
        System.out.println(regions);
        if (regions.isEmpty()) {

            Region region = new Region("for super admin", "Super Head Branch");
            regionRepository.save(region);

            return "message/added";
        }
        return "message/init";

    }
}
