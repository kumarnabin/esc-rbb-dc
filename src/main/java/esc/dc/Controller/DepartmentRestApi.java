package esc.dc.Controller;

import esc.dc.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DepartmentRestApi {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/test/dd")
    public ResponseEntity<List<Map<String,Integer>>> test(){
        return new ResponseEntity<List<Map<String, Integer>>>(
                departmentService.findAll().parallelStream().map(res->{
                    Map<String,Integer> map = new HashMap<>();
                    map.put(res.getName(),res.getDartaDists().size());
                    return map;
                }).collect(Collectors.toList()), HttpStatus.OK
        );
    }

}
