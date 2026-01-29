
package com.milestone.four.controller;

import com.milestone.four.service.TestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui")
public class UITestController {

    @Autowired
    private TestExecutionService service;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Initialize message as empty so Thymeleaf has a variable to bind to
        model.addAttribute("message", ""); 
        return "dashboard";
    }

    @PostMapping("/run")
    public String runSuite(@RequestParam(defaultValue = "testng.xml") String suite, Model model) {
        String result = service.executeSuite(suite);
        model.addAttribute("message", result);
        return "dashboard"; 
    }
}