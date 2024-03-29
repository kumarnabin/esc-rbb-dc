package esc.dc.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = {"","/","/login"}, method = RequestMethod.GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println(auth.getAuthorities());
            return "redirect:/dashboard";
        }

        return "login";
    }

    @GetMapping(value = "403")
    public String accessDenie(){
        return "message/accessDenied";
    }

    @RequestMapping(value = {"/dashboard/admin","/dashboard/create","/dashboard/view"}, method = RequestMethod.GET)
    public String adminDash() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println(auth.getAuthorities());
            return "redirect:/dashboard";
        }

        return "login";
    }
}
