package ru.projects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @RequestMapping
    public String showHome(Model model) {
        return "index";
    }
}
