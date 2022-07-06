package esc.dc.Controller;

import esc.dc.Model.Department;
import esc.dc.Model.User;
import esc.dc.Service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelAndView modelAndView;

    @RequestMapping(value = "/view/department",method = RequestMethod.GET)
    public String listDep(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("departments",departmentService.findAllByRegionId(user.getRegion().getId()));
        return "department/list";
    }

    @RequestMapping(value = "/create/department",method = RequestMethod.GET)
    public String createDep(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("department",new Department());
        return "department/create";
    }

    @RequestMapping(value = "/create/department",method = RequestMethod.POST)
    public String saveDep(Model model, Department department){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        department.setRegionId(user.getRegion().getId());
        departmentService.saveData(department);

        model.addAttribute("success", "Created successfully");
        model.addAttribute("department",new Department());
        return "department/create";
    }

    @RequestMapping(value = "/create/department/{id}",method = RequestMethod.GET)
    public String updateDep(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("department",departmentService.findOneByIdAndRegionId(id,user.getRegion().getId()));
        return "department/update";
    }

    @RequestMapping(value = "/create/department/{id}",method = RequestMethod.POST)
    public String saveUpdateDep(Model model, Department department){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        department.setRegionId(user.getRegion().getId());
        departmentService.saveData(department);
        model.addAttribute("success", "Updated successfully");
        model.addAttribute("departments", departmentService.findAllByRegionId(user.getRegion().getId()));
        return "department/list";
    }

    @RequestMapping("/test/department-count")
    public ModelAndView searchme(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.setViewName("department/count");
        System.out.println(departmentService.findAllByRegionId(user.getRegion().getId()));
        modelAndView.addObject("departments",departmentService.findAllByRegionId(user.getRegion().getId()));
        return modelAndView;
    }

}
