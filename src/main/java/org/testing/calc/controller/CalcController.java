package org.testing.calc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.testing.calc.dao.entity.CalcInfo;
import org.testing.calc.service.CalcService;

@RestController
public class CalcController {

    private final CalcService calcService;

    @Autowired
    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @PostMapping("/enter")
    public ResponseEntity<String> saveData(@RequestBody CalcInfo calcInfo) {
        try {
            calcService.saveCalcData(calcInfo);
            return ResponseEntity.ok("Data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving data: " + e.getMessage());
        }
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> fetchData(@PathVariable String key) {
        Integer result = calcService.fetchCalcData(key);
        if (result != null) {
            return ResponseEntity.ok(String.valueOf(result));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("featureB")
    public String featureB() {
        return "featureB";
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        //featureA1
        return ResponseEntity.ok("hello is returned");
    }


}


