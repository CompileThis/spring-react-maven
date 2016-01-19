package com.compilethis.srm.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping()
    public String root() {

        return "redirect:/web/";
    }

    @RequestMapping("web")
    public String home() {

        return "home";
    }
}
