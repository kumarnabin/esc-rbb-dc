package esc.dc.Controller;

import esc.dc.Model.Office;
import esc.dc.Model.User;
import esc.dc.Repository.ChalaniRepository;
import esc.dc.Repository.RegionRepository;
import esc.dc.Repository.RoleRepository;
import esc.dc.Service.UserService;
import esc.dc.common.ComplexOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ChalaniRepository chalaniRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/superadmin/testCreate",method = RequestMethod.GET)
    public String testData(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("region",regionRepository.findAll());
        model.addAttribute("role",roleRepository.findAll());
        return "superAdmin/user/createTest";
    }

    @RequestMapping(value = "/test/page",method = RequestMethod.GET)
    public String testpage(Model model) throws Exception {
        System.out.println("testing chalani list");
        List<Object[]> list = chalaniRepository.countTotalByOfficeData();
        List<ComplexOffice> complexOffices = list.parallelStream().map(result->{
            Office office=(Office) result[0];
            long count = (long)result[1];
            return new ComplexOffice(count,office);
        }).collect(Collectors.toList());
        System.out.println(complexOffices);
//        model.addAttribute("user",new User());
        model.addAttribute("region",regionRepository.findAll());
//        model.addAttribute("role",roleRepository.findAll());
        return "test/pagetwo";
    }



    @GetMapping(value = "/username")
    @ResponseBody
    public User currentUserName(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName());
    }
    @GetMapping(value = "/auth")
    @ResponseBody
    public String authUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user.getName();
    }
}
