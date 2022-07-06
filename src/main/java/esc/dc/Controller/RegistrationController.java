package esc.dc.Controller;

import esc.dc.Model.Role;
import esc.dc.Model.User;
import esc.dc.Repository.RegionRepository;
import esc.dc.Repository.RoleRepository;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerNew(Model model) {

        List<User> user1 = userService.findAllUser();

        if (user1.isEmpty()) {
            List<Role> role = roleRepository.findAll();System.out.println(role);
            model.addAttribute("role", role);
            model.addAttribute("region", regionRepository.findAll());
            model.addAttribute("user", new User());
            return "registration";

        } else {
            return "message/init";
        }
    }

    @PostMapping
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {

        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errormsg", "field invalid");
            return "admin/user/create";
        }
        userService.saveUserBySuper(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        return "message/added";
    }
}
