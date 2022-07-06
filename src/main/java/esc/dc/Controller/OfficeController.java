package esc.dc.Controller;

import esc.dc.Model.Office;
import esc.dc.Model.User;
import esc.dc.Service.OfficeService;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/view/office", method = RequestMethod.GET)
    public String viewOffice(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
        return "office/list";
    }


    @RequestMapping(value = "/create/office", method = RequestMethod.GET)
    public String create(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("office", new Office());
        return "office/create";
    }

    @RequestMapping(value = "/create/office", method = RequestMethod.POST)
    public String createRecord(@Valid Office office, BindingResult bindingResult, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        office.setRegionId(user.getRegion().getId());
//        System.out.println(office);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            return "office/create";
        } else {

            officeService.save(office);
            model.addAttribute("success", "Created successfully");
            model.addAttribute("office", new Office());
            return "office/create";
        }
    }

    @RequestMapping(value = "/create/office/{id}", method = RequestMethod.GET)
    public String showUpdateOffice(@PathVariable("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());
        model.addAttribute("user1", user.getId());
        model.addAttribute("office", officeService.findOneAndRegion(id,user.getRegion().getId()));
        return "office/update";
    }


    @RequestMapping(value = "/create/office/{id}", method = RequestMethod.POST)
    public String updateOffice(Model model, @Valid Office office, BindingResult bindingResult) {

        System.out.println("post executed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        office.setRegionId(user.getRegion().getId());
        System.out.println(office);
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            return "office/update";
        } else {

            officeService.save(office);
            model.addAttribute("success", "Updated successfully");
            model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
            return "office/list";
        }

    }
}
