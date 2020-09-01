package ru.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {

    @RequestMapping()
    public String show(Model model) {
        return "index";
    }
}
