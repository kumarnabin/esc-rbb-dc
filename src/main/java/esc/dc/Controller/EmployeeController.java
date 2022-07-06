package esc.dc.Controller;

import esc.dc.Model.Employee;
import esc.dc.Model.User;
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

@Controller
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/view/employee",method = RequestMethod.GET)
    public String listDep(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("employees",employeeService.findAllByRegionId(user.getRegion().getId()));
        return "employee/list";
    }

    @RequestMapping(value = "/create/employee",method = RequestMethod.GET)
    public String createDep(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("employee",new Employee());
        return "employee/create";
    }

    @RequestMapping(value = "/create/employee",method = RequestMethod.POST)
    public String saveDep(Model model, Employee employee){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        employee.setRegionId(user.getRegion().getId());
        employeeService.saveData(employee);

        model.addAttribute("success", "Created successfully");
        model.addAttribute("employee",new Employee());
        return "employee/create";
    }

    @RequestMapping(value = "/create/employee/{id}",method = RequestMethod.GET)
    public String updateDep(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("employee",employeeService.findByIdAndRegionId(id,user.getRegion().getId()));
        return "employee/update";
    }

    @RequestMapping(value = "/create/employee/{id}",method = RequestMethod.POST)
    public String saveUpdateDep(Model model, Employee employee){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        employee.setRegionId(user.getRegion().getId());
        employeeService.saveData(employee);
        model.addAttribute("success", "Updated successfully");
        model.addAttribute("employees", employeeService.findAllByRegionId(user.getRegion().getId()));
        return "employee/list";
    }
}
