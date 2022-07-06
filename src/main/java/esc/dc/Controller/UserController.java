package esc.dc.Controller;

import esc.dc.Model.User;
import esc.dc.Repository.RoleRepository;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public String listUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());
        System.out.println(user1);
        System.out.println("get executed");
        int regId = user1.getRegion().getId();
        System.out.println(regId);
        List<User> listUser = userService.findAllByRegion(user1.getRegion());

        model.addAttribute("users", listUser);
        return "user/list";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "user/create";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());

        User userExists = userService.findUserByEmail(user.getEmail());
        System.out.println(userExists);
        if (null == userExists) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errormsg", "field invalid");
            }
            System.out.print("post executed");
            System.out.println(user);
            userService.saveUserByAdmin(user, user1.getRegion().getId());
            model.addAttribute("successMessage", "User has been registered successfully");
            return "user/create";
        }
        model.addAttribute("errormsg", "User already Exists");
        model.addAttribute("user", new User());
        return "user/create";
    }

    @RequestMapping(value = "/view/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());
//        model.addAttribute("userName", user1.getName() + " " + user1.getLastName());
        model.addAttribute("userdetail", user1);
        return "user/profile";
    }

    @RequestMapping(value = "/view/updateUserPassword", method = RequestMethod.GET)
    public String updateUserPassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        int userid = user.getId();
//        model.addAttribute("userName", user.getName() + " " + user.getLastName());
        model.addAttribute("userToChange", user);
        return "user/updateUserPassword";
    }

    @RequestMapping(value = "/view/updateUserPassword", method = RequestMethod.POST)
    public String saveUserPassword(Model model, @RequestParam("oldPassword") String oldPassword, User userGet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        boolean check;
        check = userService.checkOldPassword(user, oldPassword);
        System.out.println(check);

        if (check) {
            userService.saveUserByAdmin(userGet, user.getRegion().getId());
            model.addAttribute("successMessage", "Password Changed");
            model.addAttribute("userdetail", user);
            return "user/profile";
        }
        model.addAttribute("errorMessage", "Old password not matched");
        model.addAttribute("userToChange", user);
        return "user/updateUserPassword";
    }
}
