package esc.dc.Controller;

import esc.dc.Model.Place;
import esc.dc.Model.User;
import esc.dc.Service.OfficeService;
import esc.dc.Service.PlaceService;
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
@RequestMapping(value = "/superadmin")
public class PlaceController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private OfficeService officeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/view/place", method = RequestMethod.GET)
    public String viewPlace(Model model) {
//        System.out.println(placeService.findAll());
//        System.out.println(officeService.findAllByRegionId(2));
        model.addAttribute("places", placeService.findAll());
        model.addAttribute("offices", officeService.findAllByRegionId(2));
        return "place/list";
    }


    @RequestMapping(value = "/create/place", method = RequestMethod.GET)
    public String create(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("place", new Place());
        return "place/create";
    }

    @RequestMapping(value = "/create/place", method = RequestMethod.POST)
    public String createRecord(@Valid Place place, BindingResult bindingResult, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println(place);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            return "place/create";
        } else {

            placeService.save(place);
            model.addAttribute("success", "Created successfully");
            model.addAttribute("place", new Place());
            return "place/create";
        }
    }

    @RequestMapping(value = "/update/place/{id}", method = RequestMethod.GET)
    public String showUpdatePlace(@PathVariable("id") int id, Model model) {
        model.addAttribute("place", placeService.findById(id));
        return "place/update";
    }


    @RequestMapping(value = "/create/place/{id}", method = RequestMethod.POST)
    public String updatePlace(Model model, @Valid Place place, BindingResult bindingResult) {

        System.out.println("post executed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println(place);
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            return "place/update";
        } else {

            placeService.save(place);
            model.addAttribute("success", "Updated successfully");
            model.addAttribute("places", placeService.findAll());
            return "place/list";
        }

    }
}
