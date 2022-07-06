package esc.dc.Controller;

import esc.dc.Model.DartaDist;
import esc.dc.Model.User;
import esc.dc.Service.DartaDistService;
import esc.dc.Service.DepartmentService;
import esc.dc.Service.EmployeeService;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DartaDistController {

    @Autowired
    private UserService userService;

    @Autowired
    private DartaDistService dartaDistService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ModelAndView modelAndView;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/view/dartaDist",method = RequestMethod.GET)
    public String viewDartaDist(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("dartaDists",dartaDistService.findAllByRegionId(user.getRegion().getId()));

        return "dartaDist/list";
    }

    @RequestMapping(value = "/create/dartaDist/{id}",method = RequestMethod.GET)
    public String updateDartaDist(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("dartaDist",dartaDistService.findByIdAndRegionId(id,user.getRegion().getId()));
        model.addAttribute("departments",departmentService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("employees",employeeService.findAllByRegionId(user.getRegion().getId()));
        return "dartaDist/update";
    }

    @RequestMapping(value = "/create/dartaDist/{id}",method = RequestMethod.POST)
    public String saveupdateDartaDist(Model model,DartaDist dartaDist){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        dartaDist.setStatus(true);
        dartaDist.setRegionId(user.getRegion().getId());
        dartaDistService.saveData(dartaDist);

        model.addAttribute("success", "Distribute successfully");
        model.addAttribute("dartaDists",dartaDistService.findAllByRegionId(user.getRegion().getId()));
        return "dartaDist/list";
    }

    @RequestMapping("/view/dartaDist/summary")
    public ModelAndView searchme(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.setViewName("dartaDist/summary");
        modelAndView.addObject("departments",departmentService.findAllByRegionId(user.getRegion().getId()));
        return modelAndView;
    }
}
