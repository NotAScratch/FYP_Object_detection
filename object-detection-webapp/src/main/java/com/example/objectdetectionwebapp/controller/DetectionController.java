package com.example.objectdetectionwebapp.controller;

import com.example.objectdetectionwebapp.model.User;
import com.example.objectdetectionwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DetectionController {

    @Value("${fastapi.url:http://localhost:8000}")
    private String fastApiUrl;

    private final RestTemplate restTemplate;
    private final UserService userService;

    public DetectionController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("fastApiUrl", fastApiUrl);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword());
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
    }

    @GetMapping("/detections")
    public ResponseEntity<?> getDetections() {
        try {
            String detectionsUrl = fastApiUrl + "/detections";
            ResponseEntity<List> response = restTemplate.getForEntity(detectionsUrl, List.class);
            List<Map<String, Object>> detections = response.getBody();
            if (detections == null) {
                return ResponseEntity.ok(List.of()); // Return empty list if response is null
            }
            return ResponseEntity.ok(detections);
        } catch (RestClientException e) {
            System.err.println("Error fetching detections from FastAPI: " + e.getMessage());
            return ResponseEntity.status(500).body("Error communicating with FastAPI: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error in getDetections: " + e.getMessage());
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }
}