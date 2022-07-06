package esc.dc.Controller;

import esc.dc.Model.OldChalani;
import esc.dc.Model.OldDarta;
import esc.dc.Model.User;
import esc.dc.Repository.OldChalaniRepository;
import esc.dc.Repository.OldDartaRepository;
import esc.dc.Service.UserService;
import esc.dc.Utils.ExcelGeneratorOldVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/view/oldData")
public class OldDataController {

    @Autowired
    private OldDartaRepository oldDartaRepository;

    @Autowired
    private OldChalaniRepository oldChalaniRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/oldDarta")
    public String viewOldDarta(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<OldDarta> oldDartas = oldDartaRepository.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("oldDarta",oldDartas);
        return "oldDarta/list";
    }

    @GetMapping(value = "/oldChalani")
    public String viewOldChalani(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<OldChalani> oldChalanis = oldChalaniRepository.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("oldChalani",oldChalanis);
        return "oldChalani/list";
    }

    @PostMapping(value = "/exportOldDarta")
    public ResponseEntity<InputStreamResource> excelDartaExport(
            @RequestParam(value = "dartaStartDate") String dartaStartDate,
            @RequestParam(value = "dartaEndDate") String dartaEndDate
    )throws Exception{
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

//            List<Darta> dartas = dartaService.findAllByRegionId(user.getRegion().getId());

            List<OldDarta> dartas = oldDartaRepository.findAllByRegionIdAndDartaDateBetween(user.getRegion().getId(),dartaStartDate,dartaEndDate);
            System.out.println(dartas);

            ByteArrayInputStream in = ExcelGeneratorOldVersion.dartaToExcel(dartas);
            // return IOUtils.toByteArray(in);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=olddarta.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @PostMapping(value = "/exportOldChalani")
    public ResponseEntity<InputStreamResource> excelChalaniExport(
            @RequestParam(value = "dartaStartDate") String dartaStartDate,
            @RequestParam(value = "dartaEndDate") String dartaEndDate
    )throws Exception{
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

//            List<Darta> dartas = dartaService.findAllByRegionId(user.getRegion().getId());

            List<OldChalani> chalanis = oldChalaniRepository.findAllByRegionIdAndChalaniDateBetween(user.getRegion().getId(),dartaStartDate,dartaEndDate);
            System.out.println(chalanis);

            ByteArrayInputStream in = ExcelGeneratorOldVersion.chalaniToExcel(chalanis);
            // return IOUtils.toByteArray(in);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=oldchalani.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }
}
