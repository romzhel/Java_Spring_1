package com.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class HomeController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String hello(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        return "index";
    }
}
