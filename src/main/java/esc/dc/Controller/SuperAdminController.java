package esc.dc.Controller;

import esc.dc.Model.Region;
import esc.dc.Model.User;
import esc.dc.Repository.RegionRepository;
import esc.dc.Repository.RoleRepository;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/superadmin")
public class SuperAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/dashboard")
    public String dashboardsuper(){
        return "superAdmin/dashboard";
    }

    @GetMapping(value = "/userList")
    public String userList(Model model){
        model.addAttribute("user",userService.findAllUser());
        return "superAdmin/user/list";
    }

    @GetMapping(value = "/createUser")
    public String createUser(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("region",regionRepository.findAll());
        model.addAttribute("role",roleRepository.findAll());
        return "superAdmin/user/create";
    }

    @PostMapping(value = "/createUser")
    public String saveUser(@Valid User user, BindingResult bindingResult, Model model){

        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            model.addAttribute("errormsg", "User already Exists");
            model.addAttribute("user",new User());
            return "superAdmin/user/create";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("user",new User());
            return "superAdmin/user/create";
        }
        System.out.print("post executed");
        System.out.println(user);
        userService.saveUserBySuper(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        model.addAttribute("user",new User());
        return "superAdmin/user/create";
    }

    @GetMapping(value = "/regionList")
    public String regionList(Model model){
        model.addAttribute("region",regionRepository.findAll());
        return "superAdmin/region/list";
    }

    @GetMapping(value = "/createRegion")
    public String createRegion(Model model){
        model.addAttribute("region",new Region());
        return "superAdmin/region/create";
    }

    @PostMapping(value = "/createRegion")
    public String saveRegion(Model model,Region region){
        regionRepository.save(region);
        model.addAttribute("successMessage", "Region has been saved successfully");
        model.addAttribute("region",new Region());
        return "superAdmin/region/create";
    }


    @GetMapping(value = "/profile")
    public String profileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());
//        model.addAttribute("userName", user1.getName() + " " + user1.getLastName());
        model.addAttribute("userdetail", user1);
        System.out.println("super profile called");
        return "superAdmin/user/profile";
    }

    @GetMapping(value = "/updateUserPassword")
    public String updateUserPassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("userToChange", user);
        return "superAdmin/user/updateUserPassword";
    }

    @PostMapping(value = "/updateUserPassword")
    public String saveUserPassword(Model model, @RequestParam("oldPassword") String oldPassword, User userGet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        boolean check;
        check = userService.checkOldPassword(user,oldPassword);
        System.out.println(check);

        if (check) {
            userService.saveUserBySuper(userGet);
            model.addAttribute("successMessage", "Password Changed");
            model.addAttribute("userdetail",user);
            return "superAdmin/user/profile";
        }
        model.addAttribute("errorMessage", "Old password not matched");
        model.addAttribute("userToChange",user);
        return "superAdmin/user/updateUserPassword";
    }


}
