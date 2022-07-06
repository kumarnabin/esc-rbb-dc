package esc.dc.Controller;

import esc.dc.Model.*;
import esc.dc.Repository.DocumentNumberingRepository;
import esc.dc.Repository.NumberingFactorRepository;
import esc.dc.Service.ChalaniService;
import esc.dc.Service.OfficeService;
import esc.dc.Service.UserService;
import esc.dc.Utils.ExcelGenerator;
import esc.dc.Utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ChalaniController {

    @Autowired
    private UserService userService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ChalaniService chalaniService;

    @Autowired
    private DocumentNumberingRepository documentNumberingRepository;


    @Autowired
    private NumberingFactorRepository numberingFactorRepository;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/view/chalaniCountByRegion", method = RequestMethod.GET)
    @ResponseBody
    public int getChalaniForPie() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return chalaniService.countAllByRegionId(user.getRegion().getId());
    }

    @RequestMapping(value = "/view/chalani", method = RequestMethod.GET)
    public String listChalani(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Chalani> list1 = chalaniService.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("chalanis", list1);
        return "chalani/list";
    }

    @RequestMapping(value = "/create/chalaniOffice", method = RequestMethod.GET)
    public String createChalaniOffice(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        NumberingFactor currentNF = numberingFactorRepository.findByName("chalani");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(), currentNF);

        if (null == currentDN) {
            return "errorDN";
        }
        model.addAttribute("dn", currentDN);
        model.addAttribute("regionId", user.getRegion().getId());
        model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("chalani", new Chalani());
        return "chalani/createChalaniOffice";


    }

    @RequestMapping(value = "/create/chalaniPerson", method = RequestMethod.GET)
    public String createChalaniPerson(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        NumberingFactor currentNF = numberingFactorRepository.findByName("chalani");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(), currentNF);

        if (null == currentDN) {
            return "errorDN";
        }
        model.addAttribute("dn", currentDN);
        model.addAttribute("regionId", user.getRegion().getId());
        model.addAttribute("chalani", new Chalani());
        return "chalani/createChalaniPerson";


    }

    @RequestMapping(value = "/create/chalaniOffice", method = RequestMethod.POST)
    public String saveChalaniOffice(@Valid Chalani chalani, BindingResult bindingResult, Model model) {
        System.out.println(chalani);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        chalani.setRegionId(user.getRegion().getId());
        String imageName = fileService.uploadFile(chalani.getFile());
        chalani.setImagePath(imageName);

        NumberingFactor currentNF = numberingFactorRepository.findByName("chalani");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(), currentNF);

//        model.addAttribute("dn",currentDN);

        if (bindingResult.hasErrors()) {
            model.addAttribute("dn", currentDN);
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("regionId", user.getRegion().getId());
            model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("chalani", new Chalani());
            return "chalani/createChalaniOffice";
        }
        chalaniService.saveChalani(chalani);

        //for updating net document number
        updateDN(currentDN, user.getRegion().getId(), currentNF);

        model.addAttribute("dn", currentDN);
        model.addAttribute("success", "Created successfully");
        model.addAttribute("regionId", user.getRegion().getId());
        model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("chalani", new Chalani());
        return "chalani/createChalaniOffice";

    }

    @RequestMapping(value = "/create/chalaniPerson", method = RequestMethod.POST)
    public String saveChalaniPerson(@Valid Chalani chalani, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        chalani.setRegionId(user.getRegion().getId());

        String imageName = fileService.uploadFile(chalani.getFile());
        chalani.setImagePath(imageName);

        NumberingFactor currentNF = numberingFactorRepository.findByName("chalani");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(), currentNF);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("regionId", user.getRegion().getId());
            model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("chalani", new Chalani());
            return "chalani/createChalaniPerson";
        }
        chalaniService.saveChalani(chalani);

        //for updating net document number
        updateDN(currentDN, user.getRegion().getId(), currentNF);
        model.addAttribute("dn", currentDN);

        model.addAttribute("success", "Created successfully");
        model.addAttribute("regionId", user.getRegion().getId());
        model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("chalani", new Chalani());
        return "chalani/createChalaniPerson";

    }

    @RequestMapping(value = "/create/updateChalani/{id}", method = RequestMethod.GET)
    public String updateChalani(@PathVariable("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Chalani recivChalani = chalaniService.findOneAndRegion(id, user.getRegion().getId());
        System.out.println(recivChalani);
        model.addAttribute("regionId", user.getRegion().getId());
        model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("chalani", recivChalani);
        return "chalani/update";
    }

    @RequestMapping(value = "/create/updateChalani/{id}", method = RequestMethod.POST)
    public String saveUpdateChalani(@PathVariable("id") int id, Model model, @Valid Chalani chalani, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Chalani recivChalani = chalaniService.findOneAndRegion(id, user.getRegion().getId());
        chalani.setRegionId(user.getRegion().getId());

        String imageName = fileService.uploadFile(chalani.getFile());
        if (imageName.equals("")) {
            chalani.setImagePath(recivChalani.getImagePath());
        } else {
            chalani.setImagePath(imageName);
        }
        chalani.setImagePath(imageName);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("regionId", user.getRegion().getId());
            model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("chalani", recivChalani);
            return "chalani/update";
        }
        chalaniService.saveChalani(chalani);
        model.addAttribute("success", "updated successfully");
        List<Chalani> list1 = chalaniService.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("chalanis", list1);
        return "chalani/list";
    }

    @RequestMapping(value = "/view/findOneChalaniAndRegion", method = RequestMethod.GET)
    @ResponseBody
    public Chalani findOneViewAndRegion(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return chalaniService.findOneAndRegion(id, user.getRegion().getId());
    }

    @RequestMapping(value = "/create/exportChalani", method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource> excelChalaniExport(
            @RequestParam(value = "chalaniStartDate") String chalaniStartDate,
            @RequestParam(value = "chalaniEndDate") String chalaniEndDate
    ) throws Exception {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

//            List<Chalani> chalanis = chalaniService.findAllByRegionId(user.getRegion().getId());
            List<Chalani> chalanis = chalaniService.findAllByRegionIdAndChalaniDateBetween(user.getRegion().getId(), chalaniStartDate, chalaniEndDate);
            System.out.println(chalanis);

            ByteArrayInputStream in = ExcelGenerator.chalaniToExcel(chalanis);
            // return IOUtils.toByteArray(in);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=chalani.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    public void updateDN(DocumentNumbering documentNumbering, int regionId, NumberingFactor numberingFactor) {

        String fWord;
        String sWord;
        String finalDisplayNumber;
        int updatedFirstStartNum;
        int updatedSecondStartNum;

        Optional<Integer> firstStNum = Optional.ofNullable(documentNumbering.getFirstNumberStartingNo());
        Optional<Integer> firstNumIncFac = Optional.ofNullable(documentNumbering.getFirstNumberIncreasingFactor());
        Optional<Integer> firstWrdAmt = Optional.ofNullable(documentNumbering.getFirstNumberAmt());
        Optional<Integer> secondStNum = Optional.ofNullable(documentNumbering.getSecondNumberStartingNo());
        Optional<Integer> secondNumIncFac = Optional.ofNullable(documentNumbering.getSecondNumberIncreasingFactor());
        Optional<Integer> secondWrdAmt = Optional.ofNullable(documentNumbering.getSecondNumberAmt());

        int fstNumAmt = firstWrdAmt.orElse(6);
        int fstStrtNum = firstStNum.orElse(0);
        int fstNumInFac = firstNumIncFac.orElse(0);

        int scndNumAmt = secondWrdAmt.orElse(6);
        int scndStrtNum = secondStNum.orElse(0);
        int scndNumInFac = secondNumIncFac.orElse(0);

        if (documentNumbering.getFirstWord().length() == 0) {
            fWord = "";
        } else {
            fWord = documentNumbering.getFirstWord() + "-";
        }
        if (documentNumbering.getSecondWord().length() == 0) {
            sWord = "";
        } else {
            sWord = "-" + documentNumbering.getSecondWord() + "-";
        }

        if (firstStNum.isPresent() && secondStNum.isPresent()) {
            documentNumbering.setFirstNumberStartingNo(fstStrtNum + fstNumInFac);
            documentNumbering.setSecondNumberStartingNo(scndStrtNum + scndNumInFac);
            updatedFirstStartNum = documentNumbering.getFirstNumberStartingNo();
            updatedSecondStartNum = documentNumbering.getSecondNumberStartingNo();

            String firstNumString = String.format("%0" + fstNumAmt + "d", updatedFirstStartNum);
            String secondNumString = String.format("%0" + scndNumAmt + "d", updatedSecondStartNum);
            finalDisplayNumber = fWord + firstNumString + sWord + secondNumString;

        } else if (!firstStNum.isPresent() && secondStNum.isPresent()) {
            documentNumbering.setSecondNumberStartingNo(scndStrtNum + scndNumInFac);
            updatedSecondStartNum = documentNumbering.getSecondNumberStartingNo();

            String secondNumString = String.format("%0" + scndNumAmt + "d", updatedSecondStartNum);
            finalDisplayNumber = fWord + sWord + secondNumString;
        } else if (firstStNum.isPresent()) {
            documentNumbering.setFirstNumberStartingNo(fstStrtNum + fstNumInFac);
            updatedFirstStartNum = documentNumbering.getFirstNumberStartingNo();

            String firstNumString = String.format("%0" + fstNumAmt + "d", updatedFirstStartNum);
            finalDisplayNumber = fWord + firstNumString + sWord;

        } else {
            finalDisplayNumber = fWord + sWord;

        }

        documentNumbering.setDisplayNo(finalDisplayNumber);
        documentNumbering.setRegionId(regionId);
        documentNumbering.setNumberingFactor(numberingFactor);

        documentNumberingRepository.save(documentNumbering);
    }
}