
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
    public String dashboard() {
        return "dashboard";
    }

    @PostMapping("/run")
    public String runSuite(@RequestParam String suite, Model model) {
        model.addAttribute("message", service.executeSuite(suite));
        return "dashboard";
    }
}
