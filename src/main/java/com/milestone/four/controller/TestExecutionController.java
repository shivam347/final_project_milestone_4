
package com.milestone.four.controller;

import com.milestone.four.service.TestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
public class TestExecutionController {

    @Autowired
    private TestExecutionService service;

    @PostMapping("/run")
    public String run(@RequestParam String suite) {
        System.setProperty("debug", "true");

        return service.executeSuite(suite);
    }
}
