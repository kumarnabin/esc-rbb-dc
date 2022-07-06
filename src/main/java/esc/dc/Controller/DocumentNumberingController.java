package esc.dc.Controller;

import esc.dc.Model.DocumentNumbering;
import esc.dc.Model.NumberingFactor;
import esc.dc.Model.User;
import esc.dc.Repository.DocumentNumberingRepository;
import esc.dc.Repository.NumberingFactorRepository;
import esc.dc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/dn")
public class DocumentNumberingController {

    @Autowired
    private DocumentNumberingRepository documentNumberingRepository;

    @Autowired
    private NumberingFactorRepository numberingFactorRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/list")
    public String listNum(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        model.addAttribute("listData", documentNumberingRepository.findByRegionId(user.getRegion().getId()));
        return "documentNumbering/list";
    }

    @GetMapping(value = "/create")
    public String createOne(Model model) {
        List<NumberingFactor> numberingFactors = numberingFactorRepository.findAll();
        System.out.println(numberingFactors);
        model.addAttribute("numberFactor", numberingFactors);
        model.addAttribute("object", new DocumentNumbering());
        return "documentNumbering/create";
    }

    @PostMapping(value = "/create")
    public String saveDN(Model model, DocumentNumbering documentNumbering) {
        System.out.println(documentNumbering);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        documentNumbering.setRegionId(user.getRegion().getId());
        NumberingFactor newNumberingFactor = documentNumbering.getNumberingFactor();
        DocumentNumbering existedFactor = documentNumberingRepository.findByRegionIdAndNumberingFactor(user.getRegion().getId(), newNumberingFactor);

        if (existedFactor != null) {
            model.addAttribute("errormsg", "Numbering factor value is already created");
            List<NumberingFactor> numberingFactors = numberingFactorRepository.findAll();
            model.addAttribute("numberFactor", numberingFactors);
            model.addAttribute("object", new DocumentNumbering());
            return "documentNumbering/create";
        } else {
            documentNumbering.setDisplayNo(generatedDisplayNumber(documentNumbering));
            documentNumberingRepository.save(documentNumbering);
            model.addAttribute("success", "Created successfully");
            List<NumberingFactor> numberingFactors = numberingFactorRepository.findAll();
            model.addAttribute("numberFactor", numberingFactors);
            model.addAttribute("object", new DocumentNumbering());
            return "documentNumbering/create";
        }


    }

    @GetMapping(value = "/update/{id}")
    public String updateDN(@PathVariable("id") int id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        DocumentNumbering retData = documentNumberingRepository.findByIdAndRegionId(id, user.getRegion().getId());
        System.out.println(retData);

        model.addAttribute("object", retData);
        return "documentNumbering/update";
    }

    @PostMapping(value = "/update/{id}")
    public String updatedDN(@PathVariable("id") int id, Model model, DocumentNumbering documentNumbering) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        System.out.println("post executed for update document number");

        System.out.println(documentNumbering.getFirstWord().length());
        System.out.println(documentNumbering.getSecondWord().length());

        documentNumbering.setDisplayNo(generatedDisplayNumber(documentNumbering));
        documentNumbering.setRegionId(user.getRegion().getId());

        documentNumberingRepository.save(documentNumbering);
        model.addAttribute("success", "Updated !!!!!!!!!!!!!");
        model.addAttribute("listData", documentNumberingRepository.findByRegionId(user.getRegion().getId()));
        return "documentNumbering/list";

    }

    public String generatedDisplayNumber(DocumentNumbering documentNumbering) {
        System.out.println("additional function");
        System.out.println(documentNumbering);

        String finalDisplayNumber;

        Optional<Integer> firstStNum = Optional.ofNullable(documentNumbering.getFirstNumberStartingNo());
        Optional<Integer> firstWrdAmt = Optional.ofNullable(documentNumbering.getFirstNumberAmt());
        Optional<Integer> secondStNum = Optional.ofNullable(documentNumbering.getSecondNumberStartingNo());
        Optional<Integer> secondWrdAmt = Optional.ofNullable(documentNumbering.getSecondNumberAmt());
        Optional<String> firstWrd = Optional.ofNullable(documentNumbering.getFirstWord());
        Optional<String> secondWrd = Optional.ofNullable(documentNumbering.getSecondWord());

        ////            int newPageNumber = pageNumber.isPresent() ? pageNumber.get() : 1;
        int fstNumAmt = firstWrdAmt.orElse(6);
        int fstStrtNum = firstStNum.orElse(0);

        int scndNumAmt = secondWrdAmt.orElse(6);
        int scndStrtNum = secondStNum.orElse(0);


        String fstWrd = firstWrd.map(s -> s + "-").orElse("");
        String fstWrd1 = !firstWrd.get().equals("") ? firstWrd.get() + "-" : "";

        String scndtWrd = secondWrd.map(s -> s + "-").orElse("");
        String scndtWrd1 = !secondWrd.get().equals("") ? "-" + secondWrd.get() + "-" : "";

        System.out.println("first num amt:" + fstNumAmt);
//        System.out.println("first staring numb"+ firstStNum);
        System.out.println("second num amt:" + scndNumAmt);
//        System.out.println("second staring numb"+ secondStNum);

        System.out.println("first word is:" + fstWrd1);
        System.out.println("second word is:" + scndtWrd1);


        if (firstStNum.isPresent() && secondStNum.isPresent()) {
            String firstNumString = String.format("%0" + fstNumAmt + "d", fstStrtNum);
            String secondNumString = String.format("%0" + scndNumAmt + "d", scndStrtNum);
            finalDisplayNumber = fstWrd1 + firstNumString + scndtWrd1 + secondNumString;
            System.out.println(finalDisplayNumber);
        } else if (!firstStNum.isPresent() && secondStNum.isPresent()) {
            String secondNumString = String.format("%0" + scndNumAmt + "d", scndStrtNum);
            finalDisplayNumber = fstWrd1 + scndtWrd1 + secondNumString;
            System.out.println(finalDisplayNumber);
        } else if (firstStNum.isPresent()) {
            String firstNumString = String.format("%0" + fstNumAmt + "d", fstStrtNum);
            finalDisplayNumber = fstWrd1 + firstNumString + scndtWrd1;
            System.out.println(finalDisplayNumber);
        } else {
            finalDisplayNumber = fstWrd1 + scndtWrd1;
            System.out.println(finalDisplayNumber);
        }

        return finalDisplayNumber;

    }

}
