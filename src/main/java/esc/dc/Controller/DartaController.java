package esc.dc.Controller;

import esc.dc.Model.*;
import esc.dc.Repository.DocumentNumberingRepository;
import esc.dc.Repository.NumberingFactorRepository;
import esc.dc.Service.DartaDistService;
import esc.dc.Service.DartaService;
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
public class DartaController {

    @Autowired
    private UserService userService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private DartaService dartaService;

    @Autowired
    private DartaDistService dartaDistService;

    @Autowired
    private DocumentNumberingRepository documentNumberingRepository;


    @Autowired
    private NumberingFactorRepository numberingFactorRepository;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/view/darta",method = RequestMethod.GET)
    public String listDarta(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Darta> list1 = dartaService.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("dartas",list1);
        return "darta/list";
    }

    @RequestMapping(value = "/create/dartaOffice",method = RequestMethod.GET)
    public String createDartaOffice(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        NumberingFactor currentNF = numberingFactorRepository.findByName("darta");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(),currentNF);

        if (null != currentDN) {
            model.addAttribute("dn", currentDN);
            model.addAttribute("regionId", user.getRegion().getId());
            model.addAttribute("offices", officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("darta", new Darta());
            return "darta/createDartaOffice";
        }
        return "errorDN";

    }

    @RequestMapping(value = "/create/dartaPerson",method = RequestMethod.GET)
    public String createDartaPerson(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        NumberingFactor currentNF = numberingFactorRepository.findByName("darta");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(),currentNF);

        if (null != currentDN) {
            model.addAttribute("dn",currentDN);
            model.addAttribute("regionId",user.getRegion().getId());
            model.addAttribute("darta",new Darta());
            return "darta/createDartaPerson";
        }
        return "errorDN";

    }

    @RequestMapping(value = "/create/dartaOffice",method = RequestMethod.POST)
    public String saveDartaOffice(@Valid Darta darta, BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        darta.setRegionId(user.getRegion().getId());

        String imageName = fileService.uploadFile(darta.getFile());
        darta.setImagePath(imageName);

        NumberingFactor currentNF = numberingFactorRepository.findByName("darta");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(),currentNF);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("regionId",user.getRegion().getId());
            model.addAttribute("offices",officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("darta", new Darta());
            return "darta/createDartaOffice";
        }
        dartaService.saveDarta(darta);
        updateDN(currentDN,user.getRegion().getId(),currentNF);

        saveDartaDist(darta,user.getRegion().getId());
        model.addAttribute("dn",currentDN);

        model.addAttribute("success", "Created successfully");
        model.addAttribute("regionId",user.getRegion().getId());
        model.addAttribute("offices",officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("darta",new Darta());
        return "darta/createDartaOffice";

    }

    @RequestMapping(value = "/create/dartaPerson",method = RequestMethod.POST)
    public String saveDartaPerson(@Valid Darta darta, BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        darta.setRegionId(user.getRegion().getId());

        String imageName = fileService.uploadFile(darta.getFile());
        darta.setImagePath(imageName);

        NumberingFactor currentNF = numberingFactorRepository.findByName("darta");
        DocumentNumbering currentDN = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(),currentNF);


        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("regionId",user.getRegion().getId());
            model.addAttribute("darta", new Darta());
            return "darta/createDartaPerson";
        }
        dartaService.saveDarta(darta);
        updateDN(currentDN,user.getRegion().getId(),currentNF);

        saveDartaDist(darta,user.getRegion().getId());

        model.addAttribute("dn",currentDN);
        model.addAttribute("success", "Created successfully");
        model.addAttribute("regionId",user.getRegion().getId());
        model.addAttribute("darta",new Darta());
        return "darta/createDartaPerson";

    }

    public void saveDartaDist(Darta darta, int regionId){
        DartaDist dartaDist1 = new DartaDist();
        dartaDist1.setDarta(darta);
        dartaDist1.setStatus(false);
        dartaDist1.setRegionId(regionId);
        dartaDistService.saveData(dartaDist1);
    }

    @RequestMapping(value = "/create/updateDarta/{id}",method = RequestMethod.GET)
    public String updateDarta(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Darta recivDarta = dartaService.findOneAndRegion(id,user.getRegion().getId());
        model.addAttribute("regionId",user.getRegion().getId());
        model.addAttribute("offices",officeService.findAllByRegionId(user.getRegion().getId()));
        model.addAttribute("darta",recivDarta);
        return "darta/update";
    }

    @RequestMapping(value = "/create/updateDarta/{id}",method = RequestMethod.POST)
    public String saveUpdateDarta(@PathVariable("id") int id, Model model,@Valid Darta darta, BindingResult bindingResult){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Darta recivDarta = dartaService.findOneAndRegion(id,user.getRegion().getId());
        darta.setRegionId(user.getRegion().getId());

        String imageName = fileService.uploadFile(darta.getFile());
        if(imageName.equals("")){
            darta.setImagePath(recivDarta.getImagePath());
        }else{
            darta.setImagePath(imageName);
        }
        darta.setImagePath(imageName);

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("regionId",user.getRegion().getId());
            model.addAttribute("offices",officeService.findAllByRegionId(user.getRegion().getId()));
            model.addAttribute("errormsg", "field invalid");
            model.addAttribute("darta",recivDarta);
            return "darta/update";
        }
        dartaService.saveDarta(darta);
        model.addAttribute("success", "updated successfully");
        List<Darta> list1 = dartaService.findAllByRegionId(user.getRegion().getId());
        model.addAttribute("dartas",list1);
        return "darta/list";
    }

    @RequestMapping(value = "/view/findOneDartaAndRegion",method = RequestMethod.GET)
    @ResponseBody
    public Darta findOneViewAndRegion(int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return dartaService.findOneAndRegion(id,user.getRegion().getId());
    }

    @RequestMapping(value = "/create/exportDarta",method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource> excelDartaExport(
            @RequestParam(value = "dartaStartDate") String dartaStartDate,
            @RequestParam(value = "dartaEndDate") String dartaEndDate
    )throws Exception{
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

//            List<Darta> dartas = dartaService.findAllByRegionId(user.getRegion().getId());

            List<Darta> dartas = dartaService.findAllByRegionIdAndDartaDateBetween(user.getRegion().getId(),dartaStartDate,dartaEndDate);
            System.out.println(dartas);

            ByteArrayInputStream in = ExcelGenerator.dartaToExcel(dartas);
            // return IOUtils.toByteArray(in);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=darta.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    public void updateDN(DocumentNumbering documentNumbering,int regionId,NumberingFactor numberingFactor){

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

        if(documentNumbering.getFirstWord().length()==0){
            fWord ="";
        }else{
            fWord = documentNumbering.getFirstWord()+"-";
        }
        if(documentNumbering.getSecondWord().length()==0){
            sWord ="";
        }else{
            sWord = "-"+documentNumbering.getSecondWord()+"-";
        }

        if(firstStNum.isPresent() && secondStNum.isPresent()){
            documentNumbering.setFirstNumberStartingNo(fstStrtNum+fstNumInFac);
            documentNumbering.setSecondNumberStartingNo(scndStrtNum+scndNumInFac);
            updatedFirstStartNum = documentNumbering.getFirstNumberStartingNo();
            updatedSecondStartNum = documentNumbering.getSecondNumberStartingNo();

            String firstNumString = String.format("%0" + fstNumAmt + "d", updatedFirstStartNum);
            String secondNumString = String.format("%0" + scndNumAmt + "d", updatedSecondStartNum);
            finalDisplayNumber = fWord+firstNumString+sWord+secondNumString;

        }else if(!firstStNum.isPresent()&& secondStNum.isPresent()){
            documentNumbering.setSecondNumberStartingNo(scndStrtNum+scndNumInFac);
            updatedSecondStartNum = documentNumbering.getSecondNumberStartingNo();

            String secondNumString = String.format("%0" + scndNumAmt + "d", updatedSecondStartNum);
            finalDisplayNumber = fWord+sWord+secondNumString;
        }else if(firstStNum.isPresent()){
            documentNumbering.setFirstNumberStartingNo(fstStrtNum+fstNumInFac);
            updatedFirstStartNum = documentNumbering.getFirstNumberStartingNo();

            String firstNumString = String.format("%0" + fstNumAmt + "d", updatedFirstStartNum);
            finalDisplayNumber = fWord+firstNumString+sWord;

        }else{
            finalDisplayNumber = fWord+sWord;

        }

        documentNumbering.setDisplayNo(finalDisplayNumber);
        documentNumbering.setRegionId(regionId);
        documentNumbering.setNumberingFactor(numberingFactor);

        documentNumberingRepository.save(documentNumbering);
    }
}
