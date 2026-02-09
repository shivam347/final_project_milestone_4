package com.milestone.four.controller;

import com.milestone.four.service.EmailService;
import com.milestone.four.service.TestExecutionService;

import java.io.IOException;
import java.io.File;
import java.nio.file.Path;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui")
public class UITestController {

    @Autowired
    private TestExecutionService service;

    @Autowired
    private EmailService emailService;


    // ================= DASHBOARD =================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        // Check Login
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        return "dashboard";
    }


    // ================= RUN TEST =================

    @PostMapping("/run")
    public String runSuite(@RequestParam String suite,
                           Model model,
                           HttpSession session) {

        //  Check Login
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("message",
                service.executeSuite(suite));

        return "dashboard";
    }


    // ================= DOWNLOAD REPORT =================

    @GetMapping("/download-report")
    public ResponseEntity<Resource> downloadReport(
            HttpSession session) throws IOException {

        //  Check Login
        if (session.getAttribute("loggedUser") == null) {
            return ResponseEntity
                    .status(401)
                    .build();
        }

        File file = new File("Final_Test_Report.xlsx");

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Path path = file.toPath();

        Resource resource =
                new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"TestReport.xlsx\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    // ================= SEND MAIL =================

    @PostMapping("/send-report")
    @ResponseBody
    public String sendReport(HttpSession session) {

        //  Check Login
        if (session.getAttribute("loggedUser") == null) {
            return "Unauthorized! Please login.";
        }

        try {

            File file =
                    new File("Final_Test_Report.xlsx");

            if (!file.exists()) {
                return "Report not found!";
            }

            emailService.sendReport(file);

            return "Report sent to mail successfully!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Failed to send report!";
        }
    }
}