package esc.dc.Controller.Api;

import esc.dc.Model.*;
import esc.dc.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test/api/")
class CollectionController {

    @Autowired
    UserService userService;
    @Autowired
    RegionService regionService;
    @Autowired
    PlaceService placeService;
    @Autowired
    ClassificationService classificationService;
    @Autowired
    FormService formService;
    @Autowired
    FormFieldService formFieldService;
    @Autowired
    FieldValueService fieldValueService;
    @Autowired
    FiscalYearService fiscalYearService;


    @GetMapping(value = {"/users"})
    public Map users() {
        List<User> users = userService.findAllUser();
        Map<String, List> data = new HashMap<>();
        data.put("data", users);
        return data;
    }

//    @GetMapping(value = {"/users/{id}"})
//    public Map users(@PathVariable int id) {
//        Map<String, Optional<User>> data = new HashMap<>();
//        data.put("data", userService.findById(id));
//        System.out.println(userService.findById(id));
//        return data;
//    }

    @GetMapping(value = {"/regions"})
    public Map regions() {
        List<Region> regions = regionService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", regions);
        return data;
    }

    @GetMapping(value = {"/regions/{id}"})
    public Map regions(@PathVariable int id) {
        Map<String, Optional<Region>> data = new HashMap<>();
        data.put("data", regionService.findById(id));
        return data;
    }

    @GetMapping(value = {"/places"})
    public Map places() {
        List<Place> places = placeService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", places);
        return data;
    }


    @GetMapping(value = {"/places/{id}"})
    public Map places(@PathVariable int id) {
        Map<String, Optional<Place>> data = new HashMap<>();
        data.put("data", placeService.findById(id));
        return data;
    }

    @GetMapping(value = {"/classifications"})
    public Map classifications() {
        List<Classification> classifications = classificationService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", classifications);
        return data;
    }


    @GetMapping(value = {"/classifications/{id}"})
    public Map classifications(@PathVariable int id) {
        Map<String, Optional<Classification>> data = new HashMap<>();
        data.put("data", classificationService.findById(id));
        return data;
    }

    @GetMapping(value = {"/forms"})
    public Map forms() {
        List<Form> forms = formService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", forms);
        return data;
    }


    @GetMapping(value = {"/forms/{id}"})
    public Map forms(@PathVariable int id) {
        Map<String, Optional<Form>> data = new HashMap<>();
        data.put("data", formService.findById(id));
        return data;
    }

    @GetMapping(value = {"/form-fields"})
    public Map formFields() {
        List<FormField> formFields = formFieldService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", formFields);
        return data;
    }

    @GetMapping(value = {"/form-fields/{id}"})
    public Map formFields(@PathVariable int id) {
        Map<String, Optional<FormField>> data = new HashMap<>();
        data.put("data", formFieldService.findById(id));
        return data;
    }

    @GetMapping(value = {"/field-values"})
    public Map fieldValues() {
        List<DocumentData> fieldValues = fieldValueService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", fieldValues);
        return data;
    }

    @GetMapping(value = {"/field-values/{id}"})
    public Map fieldValues(@PathVariable int id) {
        Map<String, Optional<DocumentData>> data = new HashMap<>();
        data.put("data", fieldValueService.findById(id));
        return data;
    }

    @GetMapping(value = {"/fiscal-years"})
    public Map fiscalYears() {
        List<FiscalYear> fiscalYears = fiscalYearService.findAll();
        Map<String, List> data = new HashMap<>();
        data.put("data", fiscalYears);
        return data;
    }

    @GetMapping(value = {"/fiscal-years/{id}"})
    public Map fiscalYears(@PathVariable int id) {
        Optional<FiscalYear> fiscalYear = fiscalYearService.findById(id);
        Map<String, Optional<FiscalYear>> data = new HashMap<>();
        data.put("data", fiscalYear);
        return data;
    }
}
