package esc.dc.Controller;

import esc.dc.Model.User;
import esc.dc.Service.ChalaniService;
import esc.dc.Service.DartaService;
import esc.dc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private DartaService dartaService;

    @Autowired
    private ChalaniService chalaniService;

    @GetMapping
    public String dashView(Model model) {

        LocalDate today = LocalDate.now();
        System.out.println("Current Date=" + today);
        System.out.println("dashboard");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        int total_darta = dartaService.countAllByRegionId(user.getRegion().getId());
        int total_chalani = chalaniService.countAllByRegionId(user.getRegion().getId());
        model.addAttribute("total_darta", total_darta);
        model.addAttribute("total_chalani", total_chalani);


        return "dashboard";
    }

    @GetMapping(value = "/getDartaChalaniCount")
    @ResponseBody
    public int[] dcCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        int d1 = dartaService.countAllByRegionId(user.getRegion().getId());
        System.out.println(d1);

        int c1 = chalaniService.countAllByRegionId(user.getRegion().getId());
        System.out.println(c1);
        return new int[]{d1, c1};
    }

    @GetMapping(value = "/getDartaOPCount")
    @ResponseBody
    public int[] dartaOPCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        return dartaService.countAllByRegionIdAndRecivType(user.getRegion().getId(), true);
    }

    @GetMapping(value = "/getChalaniOPCount")
    @ResponseBody
    public int[] chalaniOPCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        return chalaniService.countAllByRegionIdAndRecivType(user.getRegion().getId(), true);
    }

    @GetMapping(value = "/getDashboardData/{today}")
    @ResponseBody
    public Map<String, Integer> getDashboardData(@PathVariable String today) {
        String month_date = today.substring(0, today.length() - 2);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByEmail(auth.getName());
        int today_darta = dartaService.findAllByRegionIdAndDartaDateStartsWith(user.getRegion().getId(), today).size();
        int today_chalani = chalaniService.findAllByRegionIdAndChalaniDateStartsWith(user.getRegion().getId(), today).size();
        int this_month_darta = dartaService.findAllByRegionIdAndDartaDateStartsWith(user.getRegion().getId(), month_date).size();
        int this_month_chalani = chalaniService.findAllByRegionIdAndChalaniDateStartsWith(user.getRegion().getId(), month_date).size();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("today_darta", today_darta);
        map.put("today_chalani", today_chalani);
        map.put("this_month_darta", this_month_darta);
        map.put("this_month_chalani", this_month_chalani);
        System.out.println(month_date);

        return map;
    }
}
